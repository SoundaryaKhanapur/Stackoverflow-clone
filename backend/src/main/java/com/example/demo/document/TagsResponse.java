package com.example.demo.document;

import java.util.Date;
import java.util.List;

public class TagsResponse {
	
	private String tagId;
	
	private List<String> postsId;

	private String tagname;
	
	private int questionsCount;
	
	private Date createdAt;

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public List<String> getPostsId() {
		return postsId;
	}

	public void setPostsId(List<String> postsId) {
		this.postsId = postsId;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public int getQuestionsCount() {
		return questionsCount;
	}

	public void setQuestionsCount(int questionsCount) {
		this.questionsCount = questionsCount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "TagsResponse [tagsId=" + tagId + ", postsId=" + postsId + ", tagname=" + tagname + ", questionsCount="
				+ questionsCount + ", createdAt=" + createdAt + "]";
	}
	
}
