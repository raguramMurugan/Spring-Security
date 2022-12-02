package com.hubino.springSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hubino.springSecurity.entity.VerificationToken;

@Repository
public interface TokenRepository extends JpaRepository<VerificationToken, Long>{

	VerificationToken findByToken(String token);

}
