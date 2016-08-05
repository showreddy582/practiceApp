package com.courseapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseapp.domain.Topic;

public interface TestRepository extends JpaRepository<Topic, Long>{

	//@Query("from Topic where course.courseName=?")
	//public List<Topic>getTopicsByCourseName(String courseName);
}
