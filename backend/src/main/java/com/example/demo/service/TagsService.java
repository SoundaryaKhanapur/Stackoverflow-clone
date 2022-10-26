package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import com.example.demo.document.PostTag;
import com.example.demo.document.Questions;
import com.example.demo.document.Tags;
import com.example.demo.document.TagsResponse;
import com.example.demo.repository.PostTagRepository;
import com.example.demo.repository.TagsRepository;

@Service
public class TagsService {

	@Autowired
	private TagsRepository tagsRepository;

	@Autowired
	private PostTagRepository posttagRepository;

	public List<TagsResponse> retrieveAll() {
		List<Tags> findAll = tagsRepository.findAll();
		List<TagsResponse> tagsResponse = new ArrayList<>();


		for (Tags tags : findAll) {
			List<PostTag> postTags = posttagRepository.findByTagId(tags.getId());
			List<String> postId = new ArrayList<>();

			for (PostTag postTag : postTags) {
				postId.add(postTag.getPostId());
			}

			TagsResponse response = new TagsResponse();

			response.setTagId(tags.getId());
			response.setPostsId(postId);
			response.setTagname(tags.getTagname());
			response.setQuestionsCount(postTags.size());
			response.setCreatedAt(tags.getCreatedAt());


			tagsResponse.add(response);

		}

		tagsResponse.sort(new TagsSort());
		return tagsResponse;
	}

	public TagsResponse retrieveOne(String tagname) {
		TagsResponse response = new TagsResponse();

		Tags findByTagname = tagsRepository.findByTagname(tagname);
       if(findByTagname!=null){
		List<PostTag> postTags = posttagRepository.findByTagId((findByTagname == null)?" ":findByTagname.getId());
		List<String> postId = new ArrayList<>();

		for (PostTag postTag : postTags) {
			postId.add(postTag.getPostId());
		}

		
		response.setTagId(findByTagname.getId());
		response.setPostsId(postId);
		response.setTagname(findByTagname.getTagname());
		response.setQuestionsCount(postTags.size());
		response.setCreatedAt(findByTagname.getCreatedAt());
       }
		return response;
	}

}
