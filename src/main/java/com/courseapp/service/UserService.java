package com.courseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseapp.domain.User;
import com.courseapp.exception.UserNotFoundException;
import com.courseapp.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void delete(String userName) throws Exception{
		User user = userRepository.findOne(userName);
		if(user == null){
			throw new UserNotFoundException("User with "+userName + "Not found");
		}else{
			userRepository.delete(userName);
		}
	}
}
