Feature: Fetch Book By ID API

  Scenario: Fetch book details by ID as an admin
    Given I have valid credentials for "admin"
    When I fetch the book details by ID "1"
    Then the API response should return status code 200
    And the response should contain the book details for ID "1"

  Scenario: Fetch non-existing book details by ID as an admin
    Given I have valid credentials for "admin"
    When I fetch the book details by ID "5"
    Then the API response should return status code 404
    And the response should contain the message "Book not found"

  Scenario: Fetch book details by ID as a user
    Given I have valid credentials for "user"
    When I fetch the book details by ID "1"
    Then the API response should return status code 403
    And the response should contain the message "User is not permitted."

  Scenario: Fetch non-existing book details by ID as a user
    Given I have valid credentials for "user"
    When I fetch the book details by ID "5"
    Then the API response should return status code 403
    And the response should contain the message "User is not permitted."
