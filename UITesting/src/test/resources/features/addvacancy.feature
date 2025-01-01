Feature: Add Vacancy Functionality
  In order to manage job vacancies
  As an HR admin
  I want to add a new vacancy to the system

  Background:
    Given I have logged in to the OrangeHRM Application

  Scenario: Add a new vacancy successfully
    Given I navigate to the Recruitment page
    When I click the Vacancy tab
    And I click the Add Vacancy button
    And I fill out the vacancy details
      | JobTitle          | VacancyName                  | HiringManager          | Positions | Description                              | Active | Publish in RSS Feed and Web Page |
      | Software Engineer | Associate Software Engineer  | Senithi Tiranya Perera | 2         |Bachelor’s degree in Computer Science, IT | yes    | yes                              |
    And I save the vacancy
    Then I should see the "Edit Vacancy" page for the newly added vacancy

  Scenario Outline: Validation of vacancy details with existing inputs
    Given I navigate to the Recruitment page
    When I click the Vacancy tab
    And I click the Add Vacancy button
    And I fill out the vacancy details with existing "<JobTitle>" , "<VacancyName>" ,"<HiringManager>" , "<Positions>" and "<Description>"
    And  I click the save button
    Then I should see an error message indicating "<error_message>"
    Examples:
      | JobTitle          | VacancyName                  | HiringManager          | Positions | Description                              |  error_message  |
      | Software Engineer | Associate Software Engineer  | Senithi Tiranya Perera | 2         |Bachelor’s degree in Computer Science, IT |  Already exists |