Feature: Admin Book Creation API Functionality
  As an admin user
  I want to use the Book Creation API
  So that I can add new books and validate the API for different inputs

  Scenario: Successfully creating new books
    Given I am logged in as an "admin"
    When I create new books by sending POST requests to "/api/books" with the following details:
      | id | title | author  |
      | 1  | Book1 | Author1 |
      | 2  | Book2 | Author2 |
    Then the API should return a status code of 201 for all requests
    And the responses should confirm the books were created with:
      | id | title | author  |
      | 1  | Book1 | Author1 |
      | 2  | Book2 | Author2 |


  Scenario: Try to create a book with an already existing title
    Given I am logged in as an "admin"
    When I create a new book by sending a POST request to "/api/books" with the following details:
      | id | title | author  |
      | 3  | Book1 | Author1 |
    Then the API should return a status code of 208
    And the response should have an error message "Book Already Exists"


  Scenario: Submitting invalid data with integers for author and title
    Given I am logged in as an "admin"
    When I create a new book by sending a POST request to "/api/books" with the following details:
      | id | title | author |
      | 4  | 1234  | 6789   |
    Then the API should return a status code of 400
    And the response should have an error message "Invalid | Empty Input Parameters in the Request"


