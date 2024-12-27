Feature: Add User Functionality

  Scenario: Add a new user to the system
    Given I am logged into the OrangeHRM Application
    When I navigate to the Add User page
    And I fill in the Add User form with "Admin", "John  Doe", "Enabled", "newuser123", "Password@123", and "Password@123"
    Then The user should be added successfully
