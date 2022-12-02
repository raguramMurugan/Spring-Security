package com.hubino.springSecurity.event;

import org.springframework.context.ApplicationEvent;

import com.hubino.springSecurity.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisteredUser extends ApplicationEvent{

	private User user;
	private String applicationUrl;
	
	public RegisteredUser(User user, String applicationUrl) {
		super(user);
		this.user=user;
		this.applicationUrl=applicationUrl;
		
	}

}
