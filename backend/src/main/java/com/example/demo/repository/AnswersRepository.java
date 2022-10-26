package com.example.demo.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.document.Answers;
import com.example.demo.document.Questions;
import com.example.demo.document.Tags;

public interface AnswersRepository extends ElasticsearchRepository<Answers, String>{

	List<Answers> findByQuestionId(String questionId);
	
}
