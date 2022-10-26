package com.example.demo.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.example.demo.helper.Indices;

@Document(indexName = Indices.POST_TAG_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class PostTag {

	@Id
	@Field(type = FieldType.Keyword)
	private String id;
	
	@Field(type = FieldType.Text)
	private String postId;
	
	@Field(type = FieldType.Text)
	private String tagId;
	
	@Field(type = FieldType.Date)
	private Date createdAt;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date date) {
		this.createdAt = date;
	}

	@Override
	public String toString() {
		return "PostTag [id=" + id + ", postId=" + postId + ", tagId=" + tagId + ", createdAt=" + createdAt + "]";
	}

}
