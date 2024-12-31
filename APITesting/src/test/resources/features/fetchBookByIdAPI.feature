Feature: Fetch Book By ID API

  Scenario: Fetch book details by ID as a regular user
    Given I have valid credentials for "admin"
    When I fetch the book details by ID "1"
    Then the API response should return status code 200
    And the response should contain the book details for ID "1"

#  Scenario: Fetch book details by ID as an admin user
#    Given I have valid credentials for "admin"
#    When I fetch the book details by ID "5"
#    Then the API response should return status code 200
#    And the response should contain the book details for ID "5"
