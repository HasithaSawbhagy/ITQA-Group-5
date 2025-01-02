Feature: Admin Book Creation API Functionality
  As an admin user
  I want to use the Book Creation API
  So that I can add new books and validate the API for different inputs

  Scenario: Successfully creating a new book
    Given I am logged in as an "admin"
    When I create a new book by sending a POST request to "/api/books" with the following details:
      | id | title  | author   |
      | 1  | Book1  | Author1  |
    Then the API should return a status code of 201
    And the response should confirm the book was created with:
      | id | title  | author   |
      | 1  | Book1  | Author1  |

  Scenario: Attempting to create a book with an empty "author"
    Given I am logged in as an "admin"
    When I try to create a new book by sending a POST request to "/api/books" with the following details:
      | id | title  | author |
      | 2  | Book2  | ""     |
    Then the API should return a status code of 400
    And the error message should be "Invalid | Empty Input Parameters in the Request"

  Scenario: Attempting to create a book with an empty "title"
    Given I am logged in as an "admin"
    When I try to create a new book by sending a POST request to "/api/books" with the following details:
      | id | title | author   |
      | 3  | ""    | Author3  |
    Then the API should return a status code of 400
    And the error message should be "Invalid | Empty Input Parameters in the Request"
