
Feature: Post Book API

  Scenario: Create a book with null title as a user
    Given I have valid credentials for "user" to create book
    When I send POST request with null title
    Then The API response should return status code 400

  Scenario: Create a book with null author as a user
    Given I have valid credentials for "user" to create book
    When I send POST request with null author
    Then The API response should return status code 400

  Scenario: Create a book with an existing id as a user
    Given I have valid credentials for "user" to create book
    And there is an existing book with id "4"
    When I send a POST request with existing id "4"
    Then The API response should return status code 208
