Feature: User Management in HRM System
  In order to effectively manage system users
  As an Admin of the HRM application
  I want to assign user roles to existing users in the system successfully along with their login details

  Scenario: Create a new user with role Admin
    Given I am logged into the HRM system as Admin
    When I navigate to the "View System Users" page via the "Admin" option in the side navigation bar
    And I click the "Add" button to open the "Add User" page
    And I fill in the required fields with role "Admin", status "Enabled", employee name "John Luther Doe", username "john_luther", password "Johnevans123#", and confirm password "Johnevans123#"
    And I click the Save button
    Then I should see the View System Users page for the newly added user

  Scenario: Attempt to create a user with a username less than 5 characters
    Given I am logged into the HRM system as Admin
    When I navigate to the "View System Users" page via the "Admin" option in the side navigation bar
    And I click the "Add" button to open the "Add User" page
    And I fill in the required fields with role "Admin", status "Enabled", employee name "John Luther Doe", username "john", password "Johnevans123#", and confirm password "Johnevans123#"
    And I click the Save button
    Then I should see an error message "Should be at least 5 characters"





