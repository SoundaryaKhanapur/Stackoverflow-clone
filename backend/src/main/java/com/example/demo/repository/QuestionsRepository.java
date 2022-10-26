package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.demo.document.Questions;

public interface QuestionsRepository extends ElasticsearchRepository<Questions, String>{

	Optional<Questions> findById(String questionID);
}
