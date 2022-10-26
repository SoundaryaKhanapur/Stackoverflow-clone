package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.document.QuestionResponse;
import com.example.demo.document.TagsResponse;
import com.example.demo.service.QuestionSearchService;
import com.example.demo.service.QuestionsService;
import com.example.demo.service.TagsService;


@RestController
@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/api/search")
public class SearchController {

	private  QuestionSearchService questionSearchService;
	@Autowired
	private TagsService tagsService;

	@Autowired
	private QuestionsService qestionsService;

	@Autowired
	public SearchController(QuestionSearchService questionSearchService) { 
		this.questionSearchService = questionSearchService;
	}


	@GetMapping("/questions")
	@ResponseBody
	public List<QuestionResponse> fetchByTitle(@RequestParam(value = "title", required = false) String query) {                         
		//log.info("searching by title {}",query);
		List<QuestionResponse> questions=new ArrayList<>();
		questions = questionSearchService.processSearch(query,"title");

		//log.info("products {}",questions);
		return questions;
	}
	@GetMapping("/tagOrTitle")
	@ResponseBody
	public List<QuestionResponse> tagOrTitleSearch(@RequestParam(value = "tag", required = false) String tagname) {    
		List<QuestionResponse>  questions=new ArrayList<>();
		//log.info("searching by title {}",tagname);

		//tagname.trim().split(" ").length == 1

		if(tagname.trim().split(" ").length == 1) {
			questions = qestionsService.getTagQuestions(tagname);

			if(questions == null) {
				questions = questionSearchService.processSearch(tagname,"body");
			}
		}
		else  
			questions = questionSearchService.processSearch(tagname,"body");

		/*
		 * if(tagsResponse.getPostsId()!=null)
		 * questions=qestionsService.getAllQuestions(tagsResponse);
		 */


		//log.info("products {}",tagsResponse);

		return questions;
	}
	/*
	 * @GetMapping("/suggestions")
	 * 
	 * @ResponseBody public List<String> fetchSuggestions(@RequestParam(value = "q",
	 * required = false) String query) { log.info("fetch suggests {}",query);
	 * List<String> suggests = questionSearchService.fetchSuggestions(query);
	 * log.info("suggests {}",suggests); return suggests; }
	 */
}