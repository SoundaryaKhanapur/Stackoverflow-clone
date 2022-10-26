package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.document.Answers;
import com.example.demo.document.Comments;
import com.example.demo.document.PostTag;
import com.example.demo.document.QuestionResponse;
import com.example.demo.document.Questions;
import com.example.demo.document.Tags;
import com.example.demo.helper.Indices;
import com.example.demo.repository.AnswersRepository;
import com.example.demo.repository.CommentsRepository;
import com.example.demo.repository.PostTagRepository;
import com.example.demo.repository.TagsRepository;


@Service
public class QuestionSearchService {


	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private TagsRepository tagsRepository;

	@Autowired
	private PostTagRepository postTagRepository;
	
	@Autowired
	private AnswersRepository answersRepository;
	
	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	public QuestionSearchService(final ElasticsearchOperations elasticsearchOperations) {
		super();
		this.elasticsearchOperations = elasticsearchOperations;
	}

	public List<QuestionResponse> processSearch(final String query,String queryFrom) {
		//log.info("Search with query {}", query);

		// 1. Create query on multiple fields enabling fuzzy search
		/*
		 * QueryBuilder queryBuilder =
		 * QueryBuilders.matchQuery(searchBy,query).fuzziness("1");
		 */
		QueryBuilder queryBuilder = 
				QueryBuilders
				.matchQuery(queryFrom,query)
				.fuzziness(Fuzziness.ONE);
		/*
		 * Query searchQuery = new NativeSearchQueryBuilder() .withQuery(queryBuilder)
		 * .build();
		 */
		Query searchQuery = new NativeSearchQueryBuilder()
				.withQuery(queryBuilder)
				.build();
		// 2. Execute search
		SearchHits<Object> productHits = 
				elasticsearchOperations.search(searchQuery, Object.class,IndexCoordinates.of(Indices.QUESTION_INDEX));

		// 3. Map searchHits to product list
		List<QuestionResponse> questionResponse = new ArrayList<>();
		for(SearchHit<Object>  obj:productHits.getSearchHits()) {
			Questions question = (Questions)obj.getContent();

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
			response.setCommentsCount(findByPostId2.size());
			response.setAnswersCount(findByQuestionId.size());
			response.setTagnames(tagnames);
			response.setTitle(question.getTitle());
			response.setBody(question.getBody());
			response.setCreatedAt(question.getCreatedAt());
			response.setViews(question.getViews());
			response.setVotes(question.getVotes());
			questionResponse.add(response);
			
		}

		return questionResponse;
	}

	public List<String> fetchSuggestions(String query) {
		QueryBuilder queryBuilder = QueryBuilders
				.wildcardQuery("name", query+"*");

		Query searchQuery = new NativeSearchQueryBuilder()
				.withFilter(queryBuilder)
				.withPageable(PageRequest.of(0, 5))
				.build();

		SearchHits<Questions> searchSuggestions = 
				elasticsearchOperations.search(searchQuery, 
						Questions.class,
						IndexCoordinates.of(Indices.QUESTION_INDEX));

		List<String> suggestions = new ArrayList<String>();

		searchSuggestions.getSearchHits().forEach(searchHit->{
			suggestions.add(searchHit.getContent().getTitle());
		});
		return suggestions;
	}
}
