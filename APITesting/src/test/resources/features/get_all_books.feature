# Created by hasitha at 01/01/2025
Feature: Get All Books API

  Scenario: Get all books as an admin
    Given I have valid credentials for "admin" to get all books
    When I request to get all books
    Then the get all books API response should return status code 200
    And the response should contain a list of books

  Scenario: Get all books as a user
    Given I have valid credentials for "user" to get all books
    When I request to get all books
    Then the get all books API response should return status code 200
    And the response should contain a list of books

  Scenario: Get all books with invalid username for user
    Given I have invalid username credentials for "user" to get all books
    When I request to get all books
    Then the get all books API response should return status code 401

  Scenario: Get all books with invalid password for user
    Given I have invalid password credentials for "user" to get all books
    When I request to get all books
    Then the get all books API response should return status code 401

  Scenario: Get all books with invalid username for admin
    Given I have invalid username credentials for "admin" to get all books
    When I request to get all books
    Then the get all books API response should return status code 401

  Scenario: Get all books with invalid password for admin
    Given I have invalid password credentials for "admin" to get all books
    When I request to get all books
    Then the get all books API response should return status code 401

  Scenario: Get all books with all invalid credentials
    Given I have all invalid credentials to get all books
    When I request to get all books
    Then the get all books API response should return status code 401

  Scenario: Get all books when there are no books
    Given I have valid credentials for "admin" to get all books
    When I request to get all books
    Then the get all books API response should return status code 200