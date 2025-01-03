Feature: Admin Book Creation API Functionality
  As an admin user
  I want to use the Book Creation API
  So that I can add new books and validate the API for different inputs

  Scenario: Successfully creating new books
    Given I am logged in as an "admin"
    When I create new books by sending POST requests to "/api/books" with the following details:
      | title  | author  |
      | Book1  | Author1 |
      | Book2  | Author2 |
      | Book3  | Author3 |
    Then the API should return a status code of 201 for all requests
    And the responses should confirm the books were created with:
      | title  | author  |
      | Book1  | Author1 |
      | Book2  | Author2 |
      | Book3  | Author3 |

  Scenario: Try to create a book with an already existing title
    Given I am logged in as an "admin"
    When I create a new book by sending a POST request to "/api/books" with the following details:
      | title  | author  |
      | Book1  | Author1 |
    Then the API should return a status code of 208
    And the response should have an error message "Book Already Exists"


  Scenario: Submitting invalid data with integers for author and title
    Given I am logged in as an "admin"
    When I create a new book by sending a POST request to "/api/books" with the following details:
      | title | author |
      | 1234  | 6789   |
    Then the API should return a status code of 400
    And the response should have an error message "Invalid | Empty Input Parameters in the Request"

  Scenario: Try to create a book with null values for author and title
    Given I am logged in as an "admin"
    When I create a new book by sending a POST request to "/api/books" with the following details:
      | title | author |
      |       |        |
    Then the API should return a status code of 400
    And the response should have an error message "Mandatory parameters should not be null"
