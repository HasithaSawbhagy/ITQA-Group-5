Feature: Create User in HRM System

  Scenario: Create a new user with role Admin
    Given I am logged into the HRM system
    When I create a new user with the role "Admin", status "Enabled", employee name "John  Steve", username "john_steve", password "Johnevans123#", and confirm password "Johnevans123#"
    Then The user should be created successfully
