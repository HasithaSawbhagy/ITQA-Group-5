# Created by hasitha at 01/01/2025
Feature: Post Book API

  Scenario: Create a book with valid details as a user
    Given I have valid credentials for "user" to create a book
    When I send a POST request with valid book details
    Then the post book API response should return status code 201

  Scenario: Create a book with empty title as a user
    Given I have valid credentials for "user" to create a book
    When I send a POST request with empty title
    Then the post book API response should return status code 400

  Scenario: Create a book with empty author as a user
    Given I have valid credentials for "user" to create a book
    When I send a POST request with empty author
    Then the post book API response should return status code 400