Feature: Add Vacancy Functionality
  In order to manage job vacancies
  As an HR admin
  I want to add a new vacancy to the system

  Background:
    Given I have logged in to the OrangeHRM Application

  Scenario: Add a new vacancy successfully
    Given I am on the Recruitment page
    When I navigate to the Add Vacancy page
    And I fill out the vacancy details
      | JobTitle          | VacancyName                  | HiringManager          | Positions | Description                              | Active | Publish in RSS Feed and Web Page |
      | Software Engineer | Associate Software Engineer  | Senithi Tiranya Perera | 2         |Bachelor’s degree in Computer Science, IT | yes    | yes                              |
    And I save the vacancy
    Then I should see the "Edit Vacancy" page for the newly added vacancy
