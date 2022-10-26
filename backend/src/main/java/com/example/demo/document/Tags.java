package com.example.demo.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.example.demo.helper.Indices;

@Document(indexName = Indices.TAGS_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Tags {

	@Id
	@Field(type = FieldType.Keyword)
	private String id;
	
	@Field(type = FieldType.Text)
	private String tagname;
	
	//@Field(type = FieldType.Text)
	//private String description;
	
	@Field(type = FieldType.Date)
	private Date createdAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	/*
	 * public String getDescription() { return description; }
	 * 
	 * public void setDescription(String description) { this.description =
	 * description; }
	 */

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Tags [id=" + id + ", tagname=" + tagname + ", createdAt=" + createdAt + "]";
	}
	
}
