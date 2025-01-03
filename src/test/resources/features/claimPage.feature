
Feature: Claim Request Form Functionality

  Scenario: Submit a claim request with valid data
    Given I am on the claim request form page
    When I select "Travel Allowance" in the Event dropdown
    And I select "US Dollar" in the Currency dropdown
    And I enter "Some remarks" in the remarks
    When I click the create button
    Then I should see a success message

  Scenario: Cancel a claim request using And steps
    Given I am on the claim request form page
    And I fill in the required fields with event "Travel Allowance", currency "US Dollar", remarks "Some Remarks"
    And I click the cancel button
    Then I should be on the claim page