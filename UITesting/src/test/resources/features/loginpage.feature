Feature: Login Functionality
  In order to access the system
  As a user
  I want to be able to log in with different credentials

  Scenario: Login Successful with valid credentials
    Given I am in the login page of the OrangeHRM Application
    When I enter valid username "Admin" and password "admin123"
    Then I should be taken to the Overview page

  Scenario: Login failed with Invalid username
    Given I am in the login page of the OrangeHRM Application
    When I enter valid username "InvalidAdmin" and password "admin123"
    Then I should see an "Invalid credentials" error message

  Scenario: Login failed with invalid password
    Given I am in the login page of the OrangeHRM Application
    When I enter valid username "Admin" and password "Invalidpassword"
    Then I should see an "Invalid credentials" error message

  Scenario: Login failed with username case sensitive
    Given I am in the login page of the OrangeHRM Application
    When I enter valid username "admin" and password "admin123"
    Then I should see an "Invalid credentials" error message

  Scenario: Login failed with password case sensitive
    Given I am in the login page of the OrangeHRM Application
    When I enter valid username "Admin" and password "ADMIN123"
    Then I should see an "Invalid credentials" error message