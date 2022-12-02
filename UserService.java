package com.hubino.springSecurity.service;

import org.springframework.stereotype.Service;

import com.hubino.springSecurity.dto.UserDto;
import com.hubino.springSecurity.entity.User;

@Service
public interface UserService {

	User saveUserDetails(UserDto userdto);

	public void saveVerificationTokenForUser(User user, String token);

	String validateUserDetails(String token);

}
