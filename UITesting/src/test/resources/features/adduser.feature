Feature: Create User in HRM System

  Scenario: Create a new user with role Admin
    Given I am logged into the HRM system as Admin
    When I navigate to the "View System Users" page via the "Admin" option in the side navigation bar
    And I click the "Add" button to open the "Add User" page
    And I fill in the required fields with role "Admin", status "Enabled", employee name "John  Doe", username "john_doee", password "Johnevans123#", and confirm password "Johnevans123#"
    And I click the Save button
    Then I should see the View System Users page for the newly added user




