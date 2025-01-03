Feature: Add candidate Functionality
  In order to manage candidates
  As an HR admin
  I want to add a new candidate to the system

  Background:
    Given I have logged in to OrangeHRM Application

  Scenario: Add a new candidate successfully with resume upload
    Given I navigate to Recruitment page
    When I click the candidate tab
    And I click the Add candidate button
    And I fill out the candidate details with
      | JobTitle          | firstName  | middleName |lastName                 | contact | notes                              | email           | keywords|
      | Software Engineer | hasitha  | Saubhagya     | Dassanayaka      | 0332229988         |Bachelor’s degree in Computer Science, IT | h@gamil.com|automation,testing|
    And I upload a resume
    And I check the consent checkbox
    And I save the candidate
    Then I should see the "Edit candidate" page for the newly added candidate


#  Scenario: Add a new candidate without first name
#    Given I navigate to the Recruitment page
#    When I click the candidate tab
#    And I click the Add candidate button
#    And I fill out the candidate details with
#      | JobTitle          | firstName  | middleName |lastName                 | contact | notes                              | email           | keywords|
#      | Software Engineer || Saubhagya     | Dassanayaka      | 0332229988         |Bachelor’s degree in Computer Science, IT | h@gamil.com|automation,testing|
#    And I save the candidate
#    Then I should see the error messages
#
#  Scenario: Add a new candidate without last name
#    Given I navigate to the Recruitment page
#    When I click the candidate tab
#    And I click the Add candidate button
#    And I fill out the candidate details with
#      | JobTitle          | firstName  | middleName |lastName                 | contact | notes                              | email           | keywords|
#      | Software Engineer | hasitha  | Saubhagya     || 0332229988         |Bachelor’s degree in Computer Science, IT | h@gamil.com|automation,testing|
#    And I save the candidate
#    Then I should see the error messages

  Scenario: Add a new candidate without email
    Given I navigate to the Recruitment page
    When I click the candidate tab
    And I click the Add candidate button
    And I fill out the candidate details with
      | JobTitle          | firstName  | middleName |lastName                 | contact | notes                              | email           | keywords|
      | Software Engineer | hasitha  | Saubhagya     | Dassanayaka      | 0332229988         |Bachelor’s degree in Computer Science, IT ||automation,testing|
    And I save the candidate
    Then I should see the error messages

  Scenario: Add a new candidate without first name, last name and email
    Given I navigate to the Recruitment page
    When I click the candidate tab
    And I click the Add candidate button
    And I fill out the candidate details with
      | JobTitle          | firstName  | middleName |lastName                 | contact | notes                              | email           | keywords|
      | Software Engineer | null | Saubhagya     | null | 0332229988 |Bachelor’s degree in Computer Science, IT | null|automation,testing|
    And I save the candidate
    Then I should see the error messages