package com.hubino.springSecurity.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hubino.springSecurity.dto.UserDto;
import com.hubino.springSecurity.entity.User;
import com.hubino.springSecurity.event.RegisteredUser;
import com.hubino.springSecurity.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	@PostMapping("/registerUser")
	public String saveUserDetails(@RequestBody UserDto userdto, HttpServletRequest servletRequest)
	{
		User user=service.saveUserDetails(userdto);
		
		publisher.publishEvent(new RegisteredUser(user,applicationUrl(servletRequest)));
		
		return "User Saved";
	}
	
	@GetMapping("/Verification")
	public String verifyUserDetails(String token)
	{
		String result=service.validateUserDetails(token);
		if(result.equalsIgnoreCase("valid"))
		{
			return "User Verified Successfully";
		}
		else {
			return "Bad User";
		}
	}

	private String applicationUrl(HttpServletRequest servletRequest) {
		
		return "http://"+
		servletRequest.getServerName()+
		":"+
		servletRequest.getServerPort()+
		servletRequest.getContextPath();
	}

}
