package com.example.demo.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.example.demo.helper.Indices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = Indices.QUESTION_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Questions {

	@Id
	@Field(type = FieldType.Keyword)
	private String id;
	
	@Field(type = FieldType.Text)
	private String title;
	
	@Field(type = FieldType.Text)
	private String body;
	
	@Field(type = FieldType.Integer)
	private int views;
	
	@Field(type = FieldType.Date)
	private Date createdAt;
	
	@Field(type = FieldType.Integer)
	private String userId;
	
	@Field(type = FieldType.Integer)
	private int votes;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "Questions [id=" + id + ", title=" + title + ", body=" + body + ", views=" + views + ", createdAt="
				+ createdAt + ", userId=" + userId + ", votes=" + votes + "]";
	}

	
	
}
