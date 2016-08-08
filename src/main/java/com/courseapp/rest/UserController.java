package com.courseapp.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.courseapp.domain.Course;
import com.courseapp.domain.User;
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
	private UserService userService;

	//create user
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user) throws Exception {
		return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
	}

	//update user
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<User> update(@Valid @RequestBody User user) throws Exception {
		return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
	}

	//delete user
	@RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> delete(@PathVariable("userId") Long userId) throws Exception {
		userService.delete(userId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	//get user by userId	
	@RequestMapping(value = "{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getByUserName(@PathVariable("userId") Long userId) throws Exception {

		return new ResponseEntity<User>(userService.findByUserId(userId), HttpStatus.OK);
	}

	//get user by username	
	@RequestMapping(value = "{userName}", method = RequestMethod.GET)
	public ResponseEntity<User> getByUserName(@PathVariable("userName") String userName) throws Exception {
		if (!userName.contains(".com")) {
			userName += ".com";
		}

		return new ResponseEntity<User>(userService.findUserByName(userName), HttpStatus.OK);
	}

	//get all courses for a user
	@RequestMapping(value = "courses/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<Course>> getAllCoursesForaUser(@PathVariable("userId") Long userId)throws Exception {
		return new ResponseEntity<List<Course>>(userService.findByUserId(userId).getCourses(), HttpStatus.OK);
	}

}
