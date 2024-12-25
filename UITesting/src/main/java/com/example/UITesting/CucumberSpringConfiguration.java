package com.example.UITesting;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = UiTestingApplication.class)
public class CucumberSpringConfiguration {

}