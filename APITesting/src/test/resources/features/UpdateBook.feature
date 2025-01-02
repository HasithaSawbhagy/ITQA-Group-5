Feature: Update Book API Functionality

  Scenario: Update a book successfully
    Given I am authenticated as "admin"
    When I send a PUT request to "/api/books/1" with the following details:
      | id    | title             | author       |
      | 1     | Dog Tale     | John |
    Then I should receive a status code of 200
    And the response should contain:
      | id    | title             | author       |
      | 1     | Dog Tale     | John |

  Scenario: Update with non-existent ID
    Given I am authenticated as "admin"
    When I send a PUT request to "/api/books/9999" with the following details:
      | id    | title             | author       |
      | 9999  | Fairy         | Doe |
    Then I should receive a status code of 404
    And the response message should be "Book not found"

  Scenario: Validation failure for numeric title and author
    Given I am authenticated as "admin"
    When I send a PUT request to "/api/books/2" with the following details:
      | id    | title | author |
      | 2     | 1234 | 6789  |
    Then I should receive a status code of 400
    And the response message should be "Invalid Input Parameters"
