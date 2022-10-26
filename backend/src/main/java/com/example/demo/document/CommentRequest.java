package com.example.demo.document;


public class CommentRequest {

	private String body;
	
	private String userId;
	
	private String id;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CommentRequest [body=" + body + ", userId=" + userId + ", id=" + id + "]";
	}
	
}
