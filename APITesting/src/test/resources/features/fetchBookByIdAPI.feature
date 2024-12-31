Feature: Fetch Book By ID API

  Scenario Outline: Fetch book details by ID
    Given I have valid credentials for "<role>"
    When I fetch the book details by ID "<bookId>"
    Then the API response should return status code 200
    And the response should contain the book details for ID "<bookId>"

    Examples:
      | role   | bookId |
      | user   | 1      |
      | admin  | 2      |
