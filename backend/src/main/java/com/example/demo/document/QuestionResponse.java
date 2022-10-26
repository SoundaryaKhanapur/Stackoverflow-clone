package com.example.demo.document;

import java.util.Date;
import java.util.List;

public class QuestionResponse {

	private String questionId;
	
	private String userId;
	
	private String username;
	
	private int answersCount;
	
	private int commentsCount;
	
	private String title;
	
	private String body;
	
	private List<String> tagnames;
	
	private Date createdAt;
	
	private int views;
	
	private int votes;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAnswersCount() {
		return answersCount;
	}

	public void setAnswersCount(int answersCount) {
		this.answersCount = answersCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<String> getTagnames() {
		return tagnames;
	}

	public void setTagnames(List<String> tagnames) {
		this.tagnames = tagnames;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "QuestionResponse [questionId=" + questionId + ", userId=" + userId + ", username=" + username
				+ ", answersCount=" + answersCount + ", commentsCount=" + commentsCount + ", title=" + title + ", body="
				+ body + ", tagnames=" + tagnames + ", createdAt=" + createdAt + ", views=" + views + ", votes=" + votes
				+ "]";
	}

	
}
