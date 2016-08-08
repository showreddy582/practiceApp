package com.courseapp.rest;

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
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user) throws Exception {
		User dbUser = userService.findUserByName(user.getUserName());
		if(dbUser != null){
			if(dbUser.getPassword().equals(user.getPassword())){
				return new ResponseEntity<User>(dbUser, HttpStatus.OK);
			}else{
				return new ResponseEntity<User>(user, HttpStatus.FORBIDDEN);
			}
		}
		return new ResponseEntity<User>(user, HttpStatus.FORBIDDEN);
	}
 
	
}
