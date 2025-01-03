# Created by hasitha at 01/01/2025
Feature: Post Book API

  Scenario: Create a book with valid details as user
    Given I have valid credentials for "user" to create book
    When I send POST request with valid book details
    Then The API response should return status code 201

  Scenario: Create a book with null title as a user
    Given I have valid credentials for "user" to create book
    When I send POST request with null title
    Then The API response should return status code 400

  Scenario: Create a book with null author as a user
    Given I have valid credentials for "user" to create book
    When I send POST request with null author
    Then The API response should return status code 400
