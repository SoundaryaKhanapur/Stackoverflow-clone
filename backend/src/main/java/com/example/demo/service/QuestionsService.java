package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import com.example.demo.document.Answers;
import com.example.demo.document.Comments;
import com.example.demo.document.PostTag;
import com.example.demo.document.QuestionResponse;
import com.example.demo.document.Questions;
import com.example.demo.document.Request;
import com.example.demo.document.Tags;
import com.example.demo.document.TagsResponse;
import com.example.demo.helper.InvestApi;
import com.example.demo.repository.AnswersRepository;
import com.example.demo.repository.CommentsRepository;
import com.example.demo.repository.PostTagRepository;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.repository.TagsRepository;

@Service
public class QuestionsService {

	@Autowired
	private QuestionsRepository questionRepository;

	@Autowired
	private TagsRepository tagsRepository;

	@Autowired
	private PostTagRepository postTagRepository;

	@Autowired
	private AnswersRepository answersRepository;
	
	@Autowired
	private CommentsRepository commentsRepository;


	public void addQuestion(final Request request) {

		// check the Date part again

		// Setting question attributes
		Questions question = new Questions();
		question.setTitle(request.getTitle());
		question.setBody(request.getBody());
		question.setUserId(request.getUserId());;
		question.setCreatedAt(new Date());

		// saving the question to elasticsearch
		Questions savedQuestion = questionRepository.save(question);

		// getting last saved id for question
		String questionId = savedQuestion.getId();
		System.out.println("question saved: " + savedQuestion);

		// searching if tag already present, else set the new tag and save it
		
		
		String[] tags = request.getTags();
		
		
		for (String tagname : tags) {
			System.out.println(tagname);
			Tags tag = tagsRepository.findByTagname(tagname);
			
			if(tag != null) {
				System.out.println("tag already present");
			}
			else {
				System.out.println("adding new tag");

				Tags newTag = new Tags();
				newTag.setTagname(tagname);
				newTag.setCreatedAt(new Date());

				tag = tagsRepository.save(newTag);		
			}
			
			// getting last saved id for tag
			String tagId = tag.getId();
			System.out.println(tag);
			
			PostTag findByPostIdAndTagId = postTagRepository.findByPostIdAndTagId(questionId, tagId);
			
			// finding an entry in posttag for postId and tagID, if not present add new
			if(findByPostIdAndTagId != null) {
				System.out.println("already present: ");
			}
			else {
				PostTag postTag = new PostTag();
				postTag.setPostId(questionId);
				postTag.setTagId(tagId);
				postTag.setCreatedAt(new Date());
				findByPostIdAndTagId = postTagRepository.save(postTag);
				System.out.println("added new posttag: ");
			}
			
			System.out.println(findByPostIdAndTagId);	
		}	
				
	}


	public QuestionResponse getSingleQuestion(final String questionId) {

		Questions question = null;

		Optional<Questions> findById = questionRepository.findById(questionId);
		if(findById.isPresent()) {
			// get the question for the requested id
			question = findById.get();
			// increase the views by 1 and save it
			question.setViews(question.getViews()+1);
			question = questionRepository.save(question);
			System.out.println(question.getViews());
		}
		else {
			System.out.println("questionId not present");
		}

		List<PostTag> findByPostId = postTagRepository.findByPostId(questionId);
		List<String> tagnames = new ArrayList<>();

		for (PostTag postTag : findByPostId) {
			String tagId = postTag.getTagId();
			Optional<Tags> findById2 = tagsRepository.findById(tagId);
			tagnames.add(findById2.get().getTagname());
		}

		List<Answers> findByQuestionId = answersRepository.findByQuestionId(questionId);

		QuestionResponse response = new QuestionResponse();
		response.setQuestionId(question.getId());
		response.setUserId(question.getUserId());
		response.setUsername(question.getUserId());
		response.setAnswersCount(findByQuestionId.size());
		response.setCommentsCount(0);
		response.setTitle(question.getTitle());
		response.setBody(question.getBody());
		response.setTagnames(tagnames);
		response.setCreatedAt(question.getCreatedAt());
		response.setViews(question.getViews());
		response.setVotes(question.getVotes());


		return response;
	}

	public List<QuestionResponse> getQuestions() {

		Iterable<Questions> findAll = questionRepository.findAll();
		List<QuestionResponse> allQuestions = getAllQuestions(findAll);
		allQuestions.sort(new sortOnCreatedAt());
		return allQuestions;
		
	}

	public List<QuestionResponse> getTopQuestions() {

		Iterable<Questions> findAll = questionRepository.findAll();
		List<QuestionResponse> allQuestions = getAllQuestions(findAll);
		allQuestions.sort(new sortOnAnswersCount());
		return allQuestions;
		
	}

	public List<QuestionResponse> getTagQuestions(String tagname) {
		Tags findByTagname = tagsRepository.findByTagname(tagname);
		List<QuestionResponse> allQuestions = null;
		
		if(findByTagname != null) {
			List<PostTag> postTags = postTagRepository.findByTagId(findByTagname.getId());
			List<Questions> questions = new ArrayList<>();
			
			for (PostTag postTag : postTags) {
				Optional<Questions> question = questionRepository.findById(postTag.getPostId());
				questions.add(question.get());
			}
			
			allQuestions = getAllQuestions(questions);
			allQuestions.sort(new sortOnCreatedAt());
			
			
		}
		return allQuestions;
		
	}
	
	public List<QuestionResponse> getAllQuestions(Iterable<Questions> findAll){
		
		List<QuestionResponse> list = new ArrayList<>();
		
		for (Questions question : findAll) {

			List<PostTag> findByPostId = postTagRepository.findByPostId(question.getId());
			List<String> tagnames = new ArrayList<>();

			for (PostTag postTag : findByPostId) {
				String tagId = postTag.getTagId();
				Optional<Tags> findById2 = tagsRepository.findById(tagId);
				tagnames.add(findById2.get().getTagname());
			}

			List<Answers> findByQuestionId = answersRepository.findByQuestionId(question.getId());
			
			List<Comments> findByPostId2 = commentsRepository.findByPostId(question.getId());
			

			QuestionResponse response = new QuestionResponse();
			response.setQuestionId(question.getId());
			response.setUserId(question.getUserId());
			response.setUsername(question.getUserId());
			response.setAnswersCount(findByQuestionId.size());
			response.setCommentsCount(findByPostId2.size());
			response.setTitle(question.getTitle());
			response.setBody(question.getBody());
			response.setTagnames(tagnames);
			response.setCreatedAt(question.getCreatedAt());
			response.setViews(question.getViews());
			System.out.println(question.getVotes());
			response.setVotes(question.getVotes());

			list.add(response);
		}
		
		return list;
	}
	
	public List<QuestionResponse> getAllQuestions(TagsResponse tagsResponse){
		List<QuestionResponse> list= new ArrayList<>();
	for(String post:tagsResponse.getPostsId()) {
		Optional<Questions> question=questionRepository.findById(post);
		if (question.isPresent()) {
			QuestionResponse response = new QuestionResponse();
			response.setQuestionId(question.get().getId());
			response.setUserId(question.get().getUserId());
			response.setUsername(question.get().getUserId());
			response.setCommentsCount(0);
			response.setTitle(question.get().getTitle());
			response.setBody(question.get().getBody());
			response.setCreatedAt(question.get().getCreatedAt());
			response.setViews(question.get().getViews());
		    response.setVotes(question.get().getVotes());
		
			list.add(response);
		}
		

	}
	return list;
 }


	public void addVote(String questionId, String vote) {
		
		Questions question = null;

		Optional<Questions> findById = questionRepository.findById(questionId);
		if(findById.isPresent()) {
			// get the question for the requested id
			question = findById.get();
			
			if(vote.equals("up")) {
				question.setVotes(question.getVotes()+1);
			}
			else{
				question.setVotes(question.getVotes()-1);
			}
			
			question = questionRepository.save(question);
			
			System.out.println(question.getVotes());
		}
		else {
			System.out.println("questionId not present");
		}
	}
	
}
