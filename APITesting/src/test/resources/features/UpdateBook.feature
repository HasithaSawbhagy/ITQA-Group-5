Feature: Update Book API Functionality

  Scenario: Update all fields successfully
  Given I am authenticated as "admin"
    When I send a PUT request to "/api/books/1" with the following details:
      | id    | title             | author       |
      | 1     | Dog Tal     | John |
    Then I should receive a status code of 200
    And the response should contain:
      | id    | title             | author       |
      | 1     | Dog Tal     | John |

Scenario: Update with non-existent ID
    Given I am authenticated as "admin"
   When I send a PUT request to "/api/books/9999" with the following details:
      | id    | title             | author       |
      | 9999  | Fairy         | Doe |
    Then I should receive a status code of 404
    And the response message should be "Book not found"

#  Scenario: User authorization failure
#    Given I am authenticated as "user"
#    When I send a PUT request to "/api/books/1" with the following details:
#      | id    | title             | author       |
#      | 1     | Waves | Sara  |
#    Then I should receive a status code of 403
#    And the response message should be "Unauthorized access"