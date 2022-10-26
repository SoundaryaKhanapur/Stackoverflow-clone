package com.example.demo.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.document.Comments;
import com.example.demo.document.Tags;

public interface CommentsRepository extends ElasticsearchRepository<Comments, String>{

	List<Comments> findByPostId(String postId);
}
