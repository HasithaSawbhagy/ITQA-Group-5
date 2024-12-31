Feature: My Info Functionality
  In order to manage my personal details
  As a admin
  I want to update personal details in my profile

  Background:
    Given I am logged into the OrangeHRM Application

  Scenario: Update Personal Details
    Given I navigate to the My Info section
    When I should see my personal information
    And I update the personal details with the following values:
      | FirstName    | MiddleName | LastName   | DateOfBirth  | Nationality  | MaritalStatus  | Gender  | EmployeeId  | OtherId | DriverLicenseNumber | LicenseExpiryDate |
      | John         | Luther     | Doe        | 2020-01-12   | American     | Single         | Male    | 12345       | 6789    | ABC123              | 2025-01-10        |
    And I save the personal details
    Then the personal details should be updated successfully with the following values:
      | FirstName    | MiddleName | LastName   | DateOfBirth  | Nationality  | MaritalStatus  | Gender  | EmployeeId  | OtherId | DriverLicenseNumber | LicenseExpiryDate |
      | John         | Luther     | Doe        | 2020-01-12   | American     | Single         | Male    | 12345       | 6789    | ABC123              | 2025-01-10        |

  Scenario Outline: Validation of personal details with missing or invalid inputs
    Given I navigate to the My Info section
    When I should see my personal information
    And I update the personal details with "<FirstName>", "<LastName>", and "<EmployeeId>"
    And I save the personal details
    Then I should see an error message indicating "<error_message>"

    Examples:
      | FirstName | LastName | EmployeeId  | error_message                   |
#      |           | Doe      | 123456      | Required                        |
#      | John      |          | 123456      | Required                        |
      |           |          | 123456      | Required                        |
      | John      | Doe      | 12345678910 | Should not exceed 10 characters |