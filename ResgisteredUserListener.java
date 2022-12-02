package com.hubino.springSecurity.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.hubino.springSecurity.entity.User;
import com.hubino.springSecurity.event.RegisteredUser;
import com.hubino.springSecurity.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j

public class ResgisteredUserListener implements ApplicationListener<RegisteredUser>{

	@Autowired
	private UserService service;
	
	@Override
	public void onApplicationEvent(RegisteredUser event) {
	
		User user=event.getUser();
		String token=UUID.randomUUID().toString();
		service.saveVerificationTokenForUser(user, token);
		
		String url=event.getApplicationUrl() +"/Verification?token="+token;
		
		log.info("Click the Link to verifiy your account: {}",url);
		
		
	
	}

}
