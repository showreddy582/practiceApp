package com.courseapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseapp.domain.Course;
import com.courseapp.domain.Topic;
import com.courseapp.domain.User;
import com.courseapp.repositories.CourseRepository;
import com.courseapp.repositories.UserRepository;

@Service
@Transactional(rollbackOn = { Exception.class, RuntimeException.class })
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;

	public Course saveCourse(Course course) throws Exception{
		User user = userRepository.findByUserName(course.getAuthor());
		course.setRegisteredUsers(new ArrayList<>());
		course.getRegisteredUsers().add(user);
		course.getTopics().forEach(topic -> topic.setCourse(course));
		return courseRepository.save(course);
	}
	
	public Course getCourseById(Long courseId) throws Exception{
		Course course =  courseRepository.findOne(courseId);
		return course;
	}
	
	public List<Course>getAllCourses(){
		List<Course>courses =  courseRepository.findAll();
		courses.forEach(course -> {
			User user = userRepository.findByUserName(course.getAuthor());
			course.setAuthorFullName(user.getFName() +" "+user.getLName());
		});
		return courses;
	}
	
	public List<Course>getAllCoursesForUser(Long userId){
		return userRepository.findOne(userId).getCourses();
	}
	
	
	public void deleteCourse(Long courseId) throws Exception{
		Course course = courseRepository.findOne(courseId);
		if(course == null){
			throw new Exception("Course Not found");
		}
		courseRepository.delete(course);
	}
	
	public Course addUserToCourse(Long courseId, Long userId) throws Exception{
		Course course = getCourseById(courseId);
		User user = userRepository.findOne(userId);
		course.getRegisteredUsers().add(user);
		Course savedCourse = courseRepository.save(course);
		emailService.sendemail(user.getUserName(), "Course Application Notification", "You have successsfully registered for "+ savedCourse.getName());
		return savedCourse;
	}

	public Course removeUserFromCourse(Long courseId, Long userId) throws Exception{
		Course course = getCourseById(courseId);
		User user = userRepository.findOne(userId);
		course.getRegisteredUsers().remove(user);
		return courseRepository.save(course);
	}

	public Course addTopicToCourse(Long courseId, Topic topic) throws Exception {
		Course course = getCourseById(courseId);
		topic.setCourse(course);
		course.getTopics().add(topic);
		return courseRepository.save(course);
	}

	public Course removeTopicFromCourse(Long courseId, Topic topic) throws Exception {
		Course course = getCourseById(courseId);
		topic.setCourse(course);
		course.getTopics().remove(topic);
		return courseRepository.save(course);
	}

}
