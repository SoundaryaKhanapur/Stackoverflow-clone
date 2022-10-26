package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.document.Users;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public Boolean user(@RequestBody final Users users) {
		 return  userService.addUser(users);
	}
	
	
	@GetMapping
	public String getUser(@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password) {
		 return  userService.getUser(email,password);
	}
}
