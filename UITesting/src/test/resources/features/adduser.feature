Feature: User Management in HRM System
  In order to effectively manage system users
  As an Admin of the HRM application
  I want to assign new user roles to existing users in the system successfully along with their new role based login details

  Scenario: Assign Admin Role to a Existing User
    Given I am logged into the HRM system as Admin
    When I navigate to the "View System Users" page via the "Admin" option in the side navigation bar
    And I click the "Add" button to open the "Add User" page
    And I fill in the required fields with role "Admin", status "Enabled", employee name "James  Butler", username "james_but", password "James123#", and confirm password "James123#"
    And I click the Save button
    Then I should see the View System Users page for the newly added user

  Scenario: Attempt to Assign Admin Role to a Existing User with a New Username less than 5 Characters
    Given I am logged into the HRM system as Admin
    When I navigate to the "View System Users" page via the "Admin" option in the side navigation bar
    And I click the "Add" button to open the "Add User" page
    And I fill in the required fields with role "Admin", status "Enabled", employee name "John  Doe", username "john", password "John123#", and confirm password "John123#"
    And I click the Save button
    Then I should see an error message "Should be at least 5 characters"





