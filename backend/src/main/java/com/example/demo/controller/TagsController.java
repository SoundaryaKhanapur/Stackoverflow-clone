package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.document.Tags;
import com.example.demo.document.TagsResponse;
import com.example.demo.service.TagsService;

@RestController
@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/api/tags")
public class TagsController {

	@Autowired
	private TagsService tagsService;
	
	@GetMapping
	public List<TagsResponse> getTags() {
		return tagsService.retrieveAll();
	}
	
	@GetMapping("/{tagname}")
	public TagsResponse getSingleTag(@PathVariable String tagname) {
		return tagsService.retrieveOne(tagname);
	}
}
