Feature: Create User in HRM System

  Scenario: Create a new user with role Admin
    Given I am logged into the HRM system
    When I create a new user with the role "Admin", employee name "Joseph  Evans", username "john_doe", password "Password123#", and confirm password "Password123#"
    Then The user should be created successfully
