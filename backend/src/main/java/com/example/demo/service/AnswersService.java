package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.document.AnswerRequest;
import com.example.demo.document.Answers;
import com.example.demo.document.Questions;
import com.example.demo.helper.Indices;
import com.example.demo.repository.AnswersRepository;

@Service
public class AnswersService {

	@Autowired
	private AnswersRepository answersRepository;
	
	private final ElasticsearchOperations elasticsearchOperations;
	
	public AnswersService(ElasticsearchOperations elasticsearchOperations) {
		this.elasticsearchOperations = elasticsearchOperations;
	}
	
	public void addAnswer(AnswerRequest answerRequest) {
		
		Answers answer = new Answers();
		
		answer.setBody(answerRequest.getBody());
		answer.setCreatedAt(new Date());
		answer.setQuestionId(answerRequest.getQuestionId());
		answer.setUserId(answerRequest.getUserId());
		answer.setValid(true);
		
		answersRepository.save(answer);

		/*
		 * IndexQuery indexQuery = new IndexQueryBuilder() .withObject(answer).build();
		 * 
		 * String documentId = elasticsearchOperations .index(indexQuery,
		 * IndexCoordinates.of(Indices.ANSWERS_INDEX));
		 * 
		 * return documentId;
		 */
	}

	public List<Answers> getAnswers(String id) {
		
		QueryBuilder queryBuilder = 
			      QueryBuilders
			      .matchQuery("questionId", id);

			    Query searchQuery = new NativeSearchQueryBuilder()
			      .withQuery(queryBuilder)
			      .build();

			    SearchHits<Answers> productHits = 
			      elasticsearchOperations
			      .search(searchQuery, 
			          Answers.class,
			          IndexCoordinates.of(Indices.ANSWERS_INDEX));
			    
		List<Answers> list = new ArrayList<>();
		
		for (SearchHit<Answers> searchHit : productHits) {
			list.add(searchHit.getContent());
		}
		
		return list;
	}

	public void addVote(String answerId, String vote) {
		
		Answers answer = null;

		Optional<Answers> findById = answersRepository.findById(answerId);
		
		if(findById.isPresent()) {
			// get the question for the requested id
			answer = findById.get();
			
			if(vote.equals("up")) {
				answer.setVotes(answer.getVotes()+1);
			}
			else{
				answer.setVotes(answer.getVotes()-1);
			}
			
			answer = answersRepository.save(answer);
			
			System.out.println(answer.getVotes());
		}
		else {
			System.out.println("answerId not present");
		}
	}

	public void updateValidFlag(String answerId, boolean valid) {
		
		Answers answer = null;

		Optional<Answers> findById = answersRepository.findById(answerId);
		
		if(findById.isPresent()) {
			answer = findById.get();
			answer.setValid(valid);
			answer = answersRepository.save(answer);
		}
		else {
			System.out.println("answerId not present");
		}
		
	}
}
