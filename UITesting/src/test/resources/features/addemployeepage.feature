Feature: Add Employee Functionality
  In order to manage employee records
  As an Admin of the OrangeHRM application
  I want to add a new employee successfully

  Scenario: Add Employee with Required Fields Only
    Given I am logged in to the OrangeHRM application as an Admin
    When I click on the "PIM" option in the side navigation bar
    And I select "Add Employee" option in top navigation bar
    And I fill in the required fields with first name "John" and last name "Doe"
    And I click the "Save" button
    Then I should see the "Personal Details" page for the newly added employee

