package com.courseapp.test.service.stepdefinitions;

import java.time.LocalDate;

import org.junit.Assert;

import com.courseapp.domain.User;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserServiceStepDefs extends AbstractServiceTestDefs{

	private User userCreated;
	
	@When("^client passes user object to service$")
	public void client_passes_user_object_to_service() throws Throwable {
		User user = User.builder().userName("test@gmail.com").fName("Test").lName("Test123").password("Test123#a").phoneNumber("1234567890").dob(LocalDate.of(1984, 9, 19)).build();
		userCreated = userService.save(user);
	}

	@Then("^the user object should be created with id set and returned to the service$")
	public void the_user_object_should_be_created_with_id_set_and_returned_to_the_service() throws Throwable {		
		Assert.assertNotNull(userCreated.getUserId());
	}
}
