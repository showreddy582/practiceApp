package com.courseapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseapp.domain.User;

public interface UserRepository extends JpaRepository<User, String>{
	

}
