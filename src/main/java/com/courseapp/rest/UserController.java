package com.courseapp.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.courseapp.domain.User;
import com.courseapp.exception.UserNotFoundException;
import com.courseapp.repositories.UserRepository;
import com.courseapp.service.UserService;

//first apply @valid annotation for the object to be validated
//go to domain class apply validation annotations like notnull,past,length, pattern,email
//create a custom class which tells client what the errors are
//you need to know what exception spring throws when validation fails
//for that exception we need to define an exception handler which goes to controller advice class
//

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="{userName}",method=RequestMethod.DELETE)
	public ResponseEntity<HttpStatus>delete(@PathVariable("userName")String userName) throws Exception{
		if(!userName.contains(".com")){
			userName +=".com";
		}
		
		userService.delete(userName);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
}
