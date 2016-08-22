package com.courseapp.rest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	//create user
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> create(@Valid @RequestBody User user) throws Exception {
		logger.info("Received request to create the user "+user);
		return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User user) throws Exception {
		logger.info("received login request from user with user name " + user.getUserName());
		User dbUser = userService.findUserByName(user.getUserName());
		if (dbUser != null) {
			if (dbUser.getPassword().equals(user.getPassword())) {
				logger.info("login request succedded for user with user name " + user.getUserName());
				return new ResponseEntity<User>(dbUser, HttpStatus.OK);
			} else {
				logger.error("login request failed for user with user name " + user.getUserName());
				return new ResponseEntity<User>(user, HttpStatus.FORBIDDEN);
			}
		}
		logger.error("login request failed for user with user name " + user.getUserName());
		return new ResponseEntity<User>(user, HttpStatus.FORBIDDEN);
	}

	//update user
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<User> update(@Valid @RequestBody User user) throws Exception {
		logger.info("Received request to update the user "+user);
		return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
	}

	//delete user
	@RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> delete(@PathVariable("userId") Long userId) throws Exception {
		logger.info("Received request to delete the user with ud "+userId);
		userService.delete(userId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	//get user by userId	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<User> getByUserId(@RequestParam("userId") Long userId) throws Exception {
		logger.info("Received request to find the user with id "+userId);
		return new ResponseEntity<User>(userService.findByUserId(userId), HttpStatus.OK);
	}

	//get user by username	
	@RequestMapping(value = "{userName}", method = RequestMethod.GET)
	public ResponseEntity<User> getByUserName(@PathVariable("userName") String userName) throws Exception {
		logger.info("Received request to find the user with username "+userName);
		if (!userName.contains(".com")) {
			userName += ".com";
		}

		return new ResponseEntity<User>(userService.findUserByName(userName), HttpStatus.OK);
	}

	//get all users	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll() throws Exception {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "checkIfExists", method = RequestMethod.POST)
	public ResponseEntity<Boolean> checkIfExists(@RequestParam("userName") String userName) throws Exception {
		TimeUnit.SECONDS.sleep(5);
		return new ResponseEntity<Boolean>(userService.findUserByName(userName) != null, HttpStatus.OK);
	}
	

}
