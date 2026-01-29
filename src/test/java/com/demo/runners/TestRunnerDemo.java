package com.demo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Demo/Demo.feature",
        glue = {"com.demo.stepdefinitions", "com.demo.common", "com.demo.hooks"},
        plugin = {"pretty", "html:target/cucumber-html-report.html"}
)
@Test
public class TestRunnerDemo extends AbstractTestNGCucumberTests {

}