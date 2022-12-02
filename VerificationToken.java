package com.hubino.springSecurity.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken {
	

	private static final int ExpiredTimeOutValue=10;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	private Date expireTime;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private User user;
	
	public VerificationToken(User user, String token)
	{
		super();
		this.user=user;
		this.token=token;
		this.expireTime=calculateExpiredTime(ExpiredTimeOutValue);
	}
	
	public VerificationToken(String token)
	{
		super();
		this.token=token;
		this.expireTime=calculateExpiredTime(ExpiredTimeOutValue);
	}

	private Date calculateExpiredTime(int expiredtimeoutvalue2) {
		Calendar calendar=Calendar.getInstance();
		
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(calendar.MINUTE, expiredtimeoutvalue2);
		return new Date(calendar.getTime().getTime());
	}
}
