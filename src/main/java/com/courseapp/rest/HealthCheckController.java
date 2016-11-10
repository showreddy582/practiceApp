package com.courseapp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("healthCheck")
public class HealthCheckController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<HttpStatus> checkHealth() {
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
