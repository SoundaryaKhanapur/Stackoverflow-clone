package com.example.demo.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.example.demo.helper.Indices;

@Document(indexName = Indices.ANSWERS_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Answers {

	@Id
	@Field(type = FieldType.Keyword)
	private String id;
	
	@Field(type = FieldType.Text)
	private String body;
	
	@Field(type = FieldType.Date)
	private Date createdAt;
	
	@Field(type = FieldType.Text)
	private String questionId;
	
	@Field(type = FieldType.Text)
	private String userId;
	
	@Field(type = FieldType.Integer)
	private int votes;
	
	@Field(type = FieldType.Boolean)
	private boolean valid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "Answers [id=" + id + ", body=" + body + ", createdAt=" + createdAt + ", questionId=" + questionId
				+ ", userId=" + userId + ", votes=" + votes + ", valid=" + valid + "]";
	}

}
