package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import com.example.demo.document.CommentRequest;
import com.example.demo.document.Comments;
import com.example.demo.document.PostTag;
import com.example.demo.document.Questions;
import com.example.demo.document.Tags;
import com.example.demo.document.TagsResponse;
import com.example.demo.repository.CommentsRepository;
import com.example.demo.repository.PostTagRepository;
import com.example.demo.repository.TagsRepository;

@Service
public class CommentsService {
	
	@Autowired
	private CommentsRepository commentsRepository;

	public List<Comments> getComments(String id) {
		
		List<Comments> findByPostId = commentsRepository.findByPostId(id);
		return findByPostId;
		
	}

	public void addComment(CommentRequest commentRequest) {
		
		Comments comment = new Comments();
		
		comment.setBody(commentRequest.getBody());
		comment.setCreatedAt(new Date());
		comment.setPostId(commentRequest.getId());
		comment.setUserId(commentRequest.getUserId());
		
		commentsRepository.save(comment);
		
	}

	
}
