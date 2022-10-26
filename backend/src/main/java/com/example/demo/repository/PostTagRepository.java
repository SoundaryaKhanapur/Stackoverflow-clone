package com.example.demo.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.document.PostTag;
import com.example.demo.document.Questions;
import com.example.demo.document.Tags;

public interface PostTagRepository extends ElasticsearchRepository<PostTag, String>{
	
	PostTag findByPostIdAndTagId(String postId, String tagId);
	List<PostTag> findByPostId(String postId);
	List<PostTag> findByTagId(String tagId);
	
}
