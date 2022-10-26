package com.example.demo.service;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Service;

import com.example.demo.document.Users;
import com.example.demo.helper.Indices;
import com.example.demo.repository.UsersRepository;


@Service
public class UserService {
	
     @Autowired
	 UsersRepository usersRepository;
    private final ElasticsearchOperations elasticsearchOperations;
	
	public UserService(ElasticsearchOperations elasticsearchOperations) {
		this.elasticsearchOperations = elasticsearchOperations;
		
	}
	public Boolean addUser(Users user) {
		//usersRepository.deleteAll();
		Users userRepo=usersRepository.findByEmail(user.getEmail());
		if(userRepo !=null && user.getEmail().equals(userRepo.getEmail())) return false;
	//	user.setPassword(/* new BCryptPasswordEncoder().encode( */user.getPassword())/* ) */;
		
		user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
		user.setCreatedAt(new Date());
		IndexQuery indexQuery = new IndexQueryBuilder()
				.withObject(user).build();

		String documentId = elasticsearchOperations
				.index(indexQuery, IndexCoordinates.of(Indices.USERS_INDEX));

		return (documentId!=null && !documentId.isEmpty())?true:false;
		// TODO Auto-generated method stub
		
	}
	
	public String getUser(String email,String passowrd) {
		Users userRepo=usersRepository.findByEmail(email);
        if(userRepo != null) {
		byte[] decodedBytes = Base64.getDecoder().decode(userRepo.getPassword());
		String decodedString = new String(decodedBytes);
		if (email
				.equals(userRepo.getEmail()) && decodedString.equals(passowrd)/*
												 * && new BCryptPasswordEncoder().matches(user.getPassword(),
												 * userRepo.getPassword())
												 */) return userRepo.getFirstname();
     }
       return "-1";
	}
	
}
