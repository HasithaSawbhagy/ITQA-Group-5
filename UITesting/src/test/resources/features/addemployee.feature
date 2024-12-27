Feature: Add Employee Functionality
  In order to manage employees effectively
  As an admin user of the OrangeHRM Application

  Scenario: Successfully add an employee
    Given I am logged into the OrangeHRM Application
    When I navigate to the Add Employee page
    And I enter employee details "John" "A" "Doe"
    Then The employee should be added successfully