Feature: Add candidate Functionality
  In order to manage candidates
  As an HR admin
  I want to add a new candidate to the system

  Background:
    Given I have logged in to the OrangeHRM Application

  Scenario: Add a new candidate successfully
    Given I navigate to the Recruitment page
    When I click the candidate tab
    And I click the Add candidate button
    And I fill out the candidate details
      | JobTitle          | firstName  | middleName |lastName                 | contact | notes                              | email           |
      | Software Engineer | hasitha  | Saubhagya     | Dassanayaka      | 0332229988         |Bachelor’s degree in Computer Science, IT | h@gamil.com|
    And I save the candidate
    Then I should see the "Edit candidate" page for the newly added candidate