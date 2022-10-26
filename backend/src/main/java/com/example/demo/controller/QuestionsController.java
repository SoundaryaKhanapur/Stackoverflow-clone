package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.document.QuestionResponse;
import com.example.demo.document.Request;
import com.example.demo.service.QuestionsService;


@RestController
@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/api/question")
public class QuestionsController {

	@Autowired
	private QuestionsService questionsService;
	

	// to post a new question
	@PostMapping
	public void addQuestion(@RequestBody final Request request) {
		
		// write code to validate the request
		System.out.println("reached post controller");
		questionsService.addQuestion(request);
	}

	// to get all questions according to latest createdAt
	@GetMapping
	public List<QuestionResponse> getQuestions() {
		return questionsService.getQuestions();
	}
	
	// to get all top questions with maximum number of answer count or comments count
	@GetMapping("/top")
	public List<QuestionResponse> getTopQuestions() {
		return questionsService.getTopQuestions();
	}
	
	// to get all questions for a tagname
	@GetMapping("/tag/{tagname}")
	public List<QuestionResponse> getTagQuestions(@PathVariable final String tagname) {
		return questionsService.getTagQuestions(tagname);
	}
	
	// to get a single question
	@GetMapping("/{questionId}")
	public QuestionResponse getSingleQuestion(@PathVariable final String questionId) {
		return questionsService.getSingleQuestion(questionId);
	}
	
	@PostMapping("/vote/{questionId}/{vote}")
	public void addVote(@PathVariable final String questionId,@PathVariable final String vote) {
		
		questionsService.addVote(questionId, vote);
	}
	
}
