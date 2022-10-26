package com.example.demo.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.example.demo.helper.Indices;

@Document(indexName = Indices.USERS_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Users {

	@Id
	@Field(type = FieldType.Keyword)
	private String id;
	
	@Field(type = FieldType.Text)
	private String firstname;
	@Field(type = FieldType.Text)
	private String lastname;
	@Field(type = FieldType.Text)
	private String email;
	@Field(type = FieldType.Text)
	private String password;
	@Field(type = FieldType.Text)
	private Date createdAt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
}
