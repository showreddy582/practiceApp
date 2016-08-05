package com.courseapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseapp.domain.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>{

}
