
Feature: Delete Book API

  Scenario: Delete a book by ID as an admin
    Given I have valid credentials for "admin" to delete book
    When I delete the book with ID "1"
    Then the delete API response should return status code 200
    And the delete response should contain the message ""

  Scenario: Delete a non-existing book by ID as an admin
    Given I have valid credentials for "admin" to delete book
    When I delete the book with ID "50"
    Then the delete API response should return status code 404
    And the delete response should contain the message "Book not found"

  Scenario: Unauthorized deletion of a book as a user
    Given I have valid credentials for "user" to delete book
    When I delete the book with ID "2"
    Then the delete API response should return status code 403
    And the delete response should contain the message "User is not permitted."


  Scenario: Attempt to delete a book with invalid ID format as an admin
    Given I have valid credentials for "admin" to delete book
    When I delete the book with ID "abc"
    Then the delete API response should return status code 400
    And the delete response should contain the message ""

#  Scenario: Delete a non-existing book by ID as an user
#    Given I have valid credentials for "user" to delete book
#    When I delete the book with ID "50"
#    Then the delete API response should return status code 403
#    And the delete response should contain the message "User is not permitted."
