Feature: Add User Functionality
  In order to manage user accounts
  As an admin
  I want to add a new user to the system

  Scenario: Add a new user successfully
    Given I am logged in as an admin
    When I navigate to the Add User page
    And I fill out the user details
      | UserRole      | EmployeeName   | Status   | Username  | Password      | ConfirmPassword |
      | Admin         | John Smith     | Enabled  | johndoe   | Password@123  | Password@123    |
    And I save the new user
    Then the new user should be added successfully
