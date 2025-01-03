Feature: Book Management API Tests

  Scenario: Create a book
    Given I am an admin
    When I send a POST request to create the book
      | title      | author     |
      | BookTitle1 | AuthorName |
    Then I validate the response status is 201

  Scenario: Update a book
    Given I am an admin
    When I send a POST request to create the book
      | title      | author     |
      | BookTitle1 | AuthorName |
    When I send a PUT request to update the book
      | id | title         | author     |
      | 1  | UpdatedTitle  | AuthorName |
    Then I validate the response status is 200

  Scenario: Update a non-existing book
    Given I am an admin
    When I send a PUT request to update the book
      | id | title        | author     |
      | 99 | NonExistent  | AuthorName |
    Then I validate the response status is 404

  Scenario: Delete a book
    Given I am an admin
    When I send a POST request to create the book
      | title      | author     |
      | BookTitle1 | AuthorName |
    When I send a DELETE request for book with id 1
    Then I validate the response status is 200

  Scenario: Delete a non-existing book
    Given I am an admin
    When I send a DELETE request for book with id 99
    Then I validate the response status is 404

  Scenario: Fetch all books
    Given I am an admin
    When I send a GET request for all books
    Then I validate the response status is 200

  Scenario: Fetch a book by ID
    Given I am an admin
    When I send a POST request to create the book
      | title      | author     |
      | BookTitle1 | AuthorName |
    When I send a GET request for book with id 1
    Then I validate the response status is 200

  Scenario: Fetch a non-existing book by ID
    Given I am an admin
    When I send a GET request for book with id 99
    Then I validate the response status is 404