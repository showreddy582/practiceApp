package com.courseapp.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="Feature" ,glue={"com.courseapp.test.stepdefs"})
public class CucumberTestRunner {

}
