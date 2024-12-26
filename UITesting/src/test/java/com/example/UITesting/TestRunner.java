package com.example.UITesting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Login.feature",
        glue = "com.example.UITesting",
        plugin = {"pretty", "html:target/cucumber-report"}
)
public class TestRunner {
}