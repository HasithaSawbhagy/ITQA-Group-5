Feature: My Info Functionality
  In order to manage my personal details
  As a valid OrangeHRM user

  Scenario: Access My Info Page

    Given I am logged into the OrangeHRM Application
    When I navigate to the My Info section
    Then I should see my personal information