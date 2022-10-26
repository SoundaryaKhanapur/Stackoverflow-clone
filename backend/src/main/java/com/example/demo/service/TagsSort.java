package com.example.demo.service;

import java.util.Comparator;

import com.example.demo.document.TagsResponse;

public class TagsSort implements Comparator<TagsResponse>{

	@Override
	public int compare(TagsResponse o1, TagsResponse o2) {
		// TODO Auto-generated method stub
		return o2.getQuestionsCount() - o1.getQuestionsCount();
	}

}
