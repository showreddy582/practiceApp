package com.courseapp.test.service.stepdefinitions;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.courseapp.config.PracticeAppApplication;
import com.courseapp.service.UserService;

@ContextConfiguration(classes = PracticeAppApplication.class)
@DataJpaTest
@RunWith(SpringRunner.class)
public abstract class AbstractServiceTestDefs {

	@Autowired
	protected UserService userService;
		
}
