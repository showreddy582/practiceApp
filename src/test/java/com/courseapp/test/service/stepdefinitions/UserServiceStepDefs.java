package com.courseapp.test.service.stepdefinitions;

import org.junit.Assert;

import com.courseapp.domain.User;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserServiceStepDefs extends AbstractServiceTestDefs{

	private User userCreated;
	
	@When("^client passes user object to service$")
	public void client_passes_user_object_to_service() throws Throwable {
		User user = new User(null, "test@gmail.com", "Test", "Test123", null, "Test123#a", null, "1234567890", null, null);
		userCreated = userService.save(user);
	}

	@Then("^the user object should be created with id set and returned to the service$")
	public void the_user_object_should_be_created_with_id_set_and_returned_to_the_service() throws Throwable {
		Assert.assertNotNull(userCreated.getUserId());
	}
}
