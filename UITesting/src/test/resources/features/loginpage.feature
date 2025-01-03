Feature: Login Functionality
  In order to access the system
  As a user
  I want to be able to log in with different credentials

  Scenario: Login Successful with valid credentials
    Given I am in the login page of the OrangeHRM Application
    When I enter valid username "Admin" and password "admin123"
    Then I should be taken to the Overview page

