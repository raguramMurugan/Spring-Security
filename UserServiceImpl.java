package com.hubino.springSecurity.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hubino.springSecurity.dto.UserDto;
import com.hubino.springSecurity.entity.User;
import com.hubino.springSecurity.entity.VerificationToken;
import com.hubino.springSecurity.repository.TokenRepository;
import com.hubino.springSecurity.repository.UserRepository;

@Service

public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	TokenRepository tokenRepository;
	
	@Override
	public User saveUserDetails(UserDto userdto) {
		User user=new User();
		user.setFirstname(userdto.getFirstname());
		user.setLastname(userdto.getLastname());
		user.setEmail(userdto.getEmail());
		user.setPassword(passwordEncoder.encode(userdto.getPassword()));
		repo.save(user);
		return user;
	}

	@Override
	public void saveVerificationTokenForUser(User user, String token) {
		VerificationToken verificationToken=new VerificationToken(user,token);
		tokenRepository.save(verificationToken);
		
	}

	@Override
	public String validateUserDetails(String token) {
		VerificationToken verificationToken=tokenRepository.findByToken(token);
		if(verificationToken==null)
		{
			return "Invalid";
		}
		
		User user=verificationToken.getUser();
		Calendar cal=Calendar.getInstance();
		
		if(verificationToken.getExpireTime().getTime() - cal.getTime().getTime() <=0)
		{
			tokenRepository.delete(verificationToken);
			return "Token Expired";
		}
		user.setEnable(true);
		repo.save(user);
		return "valid";
	}

}
