package com.courseapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.courseapp.domain.Course;
import com.courseapp.domain.User;
import com.courseapp.repositories.CourseRepository;
import com.courseapp.repositories.UserRepository;

@RestController
@RequestMapping("course")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		User user = userRepository.findOne(course.getAuthor());
		course.getRegisteredUsers().add(user);
		Course savedCourse = courseRepository.save(course);	
		return new ResponseEntity<Course>(savedCourse, HttpStatus.CREATED);
	}
}
