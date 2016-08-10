package com.courseapp.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.courseapp.domain.User;
import com.courseapp.service.UserService;

@RestController
@RequestMapping("authenticate")
//@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

	final static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
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

}
