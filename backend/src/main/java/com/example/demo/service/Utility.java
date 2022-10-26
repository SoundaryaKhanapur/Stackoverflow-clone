package com.example.demo.service;

import java.util.Optional;

import com.example.demo.document.Questions;
import com.example.demo.repository.PostTagRepository;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.repository.TagsRepository;

public class Utility {

	private final QuestionsRepository questionRepository;
	private final TagsRepository tagsRepository;
	private final PostTagRepository postTagRepository;

	public Utility(QuestionsRepository questionRepository, TagsRepository tagsRepository, PostTagRepository postTagRepository) {
		this.questionRepository = questionRepository;
		this.tagsRepository = tagsRepository;
		this.postTagRepository = postTagRepository;
	}

	public Questions retrieveOne(String questionId) {

		Questions question = null;

		Optional<Questions> findById = questionRepository.findById(questionId);
		if(findById.isPresent()) {
			question = findById.get();
			question.setViews(question.getViews()+1);
			question = questionRepository.save(question);
			System.out.println(question.getViews());
		}
		else {
			System.out.println("questionId not present");
		}
		return question;

	}
}
