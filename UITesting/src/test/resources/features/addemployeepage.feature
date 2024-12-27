Feature: Add Employee Functionality
  In order to manage employee records
  As an HR representative of the OrangeHRM application
  I want to add a new employee successfully

  Scenario: Add Employee Successfully
    Given I am logged in to the OrangeHRM application with valid credentials
    When I navigate to the "Add Employee" page
    And I fill in the required fields "John" and "Doe"
    And I click the "Save" button
    Then I should see the "Personal Details" page for the newly added employee

