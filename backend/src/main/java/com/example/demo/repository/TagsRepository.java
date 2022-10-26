package com.example.demo.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.demo.document.Tags;

public interface TagsRepository extends ElasticsearchRepository<Tags, String>{

	Tags findByTagname(String tagname);
	List<Tags> findAll();
	
}
