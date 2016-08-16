package com.courseapp.test.rest.stepdefinitions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.courseapp.domain.User;
import com.courseapp.rest.UserController;
import com.courseapp.service.UserService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserControllerStepDefs extends AbstractStepDefs {
	
	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Given("^the web context is set$")
	public void the_web_context_is_set() throws Throwable {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@When("^client request POST /user with json data:$")
	public void client_request_POST_user_with_json_data(String userJson) throws Throwable {
		User savedUser = User.builder().userId(1l).userName("appi.bh@gmail.com").fName("Appi").lName("Bhimavarapu").password("Appi1234#").dob(LocalDate.of(1984, 9, 19)).build();
		//User savedUser = new User(1l,"appi.bh@gmail.com","Appi","Bhimavarapu",null,"appi1234",null,null, LocalDateTime.now(), LocalDateTime.now());
		Mockito.when(userService.save(Mockito.isA(User.class))).thenReturn(savedUser);
		resultActions = this.mockMvc.perform(post("/user").contentType(contentType).content(userJson));
	}

	@Then("^the response code should be (\\d+)$")
	public void the_response_code_should_be(int statusCode) throws Throwable {
		resultActions.andExpect(status().is(statusCode));
	}

}
