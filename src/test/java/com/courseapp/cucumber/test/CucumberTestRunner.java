package com.courseapp.cucumber.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:features",
	glue={"com.courseapp.test.rest.stepdefinitions","com.courseapp.test.service.stepdefinitions"},
	format ={"html:target/cucumber-html-report", "json:target/cucumber-json-report.json"})
public class CucumberTestRunner {
	
}
