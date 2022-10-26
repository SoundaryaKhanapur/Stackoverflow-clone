package com.example.demo.document;

import java.util.Arrays;

public class Request {

	//private String id;
	
	private String title;
	
	private String body;
	
	private String userId;
	
	private String[] tags;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Request [title=" + title + ", body=" + body + ", userId=" + userId + ", tags=" + Arrays.toString(tags)
				+ "]";
	}

	
}
