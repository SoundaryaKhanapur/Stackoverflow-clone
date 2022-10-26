package com.example.demo.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.example.demo.helper.Indices;

@Document(indexName = Indices.COMMENTS_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Comments {

	@Id
	@Field(type = FieldType.Keyword)
	private String Id;
	
	@Field(type = FieldType.Text)
	private String body;
	
	@Field(type = FieldType.Date)
	private Date createdAt;
	
	@Field(type = FieldType.Text)
	private String postId;
	
	@Field(type = FieldType.Text)
	private String userId;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Comments [Id=" + Id + ", body=" + body + ", createdAt=" + createdAt + ", postId=" + postId + ", userId="
				+ userId + "]";
	}
	
}
