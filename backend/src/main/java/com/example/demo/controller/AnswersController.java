package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.document.AnswerRequest;
import com.example.demo.document.Answers;
import com.example.demo.document.QuestionResponse;
import com.example.demo.repository.AnswersRepository;
import com.example.demo.service.AnswersService;

@RestController
@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/api/answer")
public class AnswersController {

	private final AnswersService service;

	@Autowired
	public AnswersController(AnswersService service) {
		this.service = service;
	}

	@PostMapping
	public void addAnswer(@RequestBody final AnswerRequest answer) {
		service.addAnswer(answer);
	}

	@GetMapping("/{id}")
	public List<Answers> getAnswers(@PathVariable String id) {
		return service.getAnswers(id);
	}
	
	@PostMapping("/vote/{answerId}/{vote}")
	public void addVote(@PathVariable final String answerId,@PathVariable final String vote) {
		
		service.addVote(answerId, vote);
	}
	
	@PostMapping("/{answerId}/{valid}")
	public void updateValidFlag(@PathVariable final String answerId,@PathVariable final boolean valid) {
		
		service.updateValidFlag(answerId, valid);
	}
}
