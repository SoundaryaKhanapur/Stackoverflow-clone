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

import com.example.demo.document.CommentRequest;
import com.example.demo.document.Comments;
import com.example.demo.service.CommentsService;


@RestController
@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/api/comments")
public class CommentsController {

	@Autowired
	private CommentsService commentsService;
	
	@GetMapping("/{id}")
	public List<Comments> getComments(@PathVariable String id) {
		
		return commentsService.getComments(id);
	}
	
	@PostMapping
	public void addComment(@RequestBody CommentRequest commentRequest) {
		
		commentsService.addComment(commentRequest);
		
	}
	
}
