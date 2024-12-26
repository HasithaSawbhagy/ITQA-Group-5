Feature: Login Functionality
  In order to do internet banking
  As a valid Para Bank customer

Scenario: Login Successful

Given I am in the login page of the OrangeHRM Application
When I enter valid credentials
Then I should be taken to the Overview page