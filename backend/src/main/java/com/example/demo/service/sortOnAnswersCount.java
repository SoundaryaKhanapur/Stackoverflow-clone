package com.example.demo.service;

import java.util.Comparator;

import com.example.demo.document.QuestionResponse;

public class sortOnAnswersCount implements Comparator<QuestionResponse> {

	@Override
	public int compare(QuestionResponse o1, QuestionResponse o2) {
		return o2.getAnswersCount() - o1.getAnswersCount();
	}

}
