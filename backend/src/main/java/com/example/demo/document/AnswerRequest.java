package com.example.demo.document;


public class AnswerRequest {

	private String body;
	
	private String userId;
	
	private String questionId;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "AnswerRequest [body=" + body + ", userId=" + userId + ", questionId=" + questionId + "]";
	}
	
}
