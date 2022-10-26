package com.example.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.document.Users;

public interface UsersRepository extends ElasticsearchRepository<Users, String>{

	Users findByEmail(String email);
	
}
