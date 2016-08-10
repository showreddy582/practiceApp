package com.courseapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.courseapp.domain.Course;
import com.courseapp.domain.Topic;
import com.courseapp.service.CourseService;

@RestController
@RequestMapping("course")
//@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

	@Autowired
	private CourseService courseService;

	/**
	 * Creates a new course
	 * @param course
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Course> create(@RequestBody Course course) throws Exception {
		return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED);
	}

	//update course
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Course> update(@RequestBody Course course) throws Exception {
		return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.OK);
	}

	//get course by id
	@RequestMapping(value="{courseId}",method = RequestMethod.GET)
	public ResponseEntity<Course> update(@PathVariable("courseId") Long courseId) throws Exception {
		return new ResponseEntity<Course>(courseService.getCourseById(courseId), HttpStatus.OK);
	}

	//delete course by id
	@RequestMapping(value="{courseId}",method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> delete(@PathVariable("courseId") Long courseId) throws Exception {
		courseService.deleteCourse(courseId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	//add User to course
	@RequestMapping(value="{courseId}/users/{userId}",method = RequestMethod.PUT)
	public ResponseEntity<Course> addUserToCourse(@PathVariable("courseId")Long courseId, @PathVariable("userId")Long userId) throws Exception {
		return new ResponseEntity<Course>(courseService.addUserToCourse(courseId, userId), HttpStatus.OK);
	}
	
	//remove User from course
	@RequestMapping(value="{courseId}/users/{userId}",method = RequestMethod.DELETE)
	public ResponseEntity<Course> removeUserFromCourse(@PathVariable("courseId")Long courseId, @PathVariable("userId")Long userId) throws Exception {
		return new ResponseEntity<Course>(courseService.removeUserFromCourse(courseId, userId), HttpStatus.OK);
	}
	
	//add Topic to a course
	@RequestMapping(value="{courseId}/topic",method = RequestMethod.PUT)
	public ResponseEntity<Course> addTopicToCourse(@PathVariable("courseId")Long courseId, @RequestBody Topic topic) throws Exception {
		
		return new ResponseEntity<Course>(courseService.addTopicToCourse(courseId, topic), HttpStatus.OK);
	}
	
	//remove Topic from a course
	@RequestMapping(value="{courseId}/topic",method = RequestMethod.DELETE)
	public ResponseEntity<Course> removeTopicFromACourse(@PathVariable("courseId")Long courseId, @RequestBody Topic topic) throws Exception {
		
		return new ResponseEntity<Course>(courseService.removeTopicFromCourse(courseId, topic), HttpStatus.OK);
	}



}
