Feature: My Info Functionality
  In order to manage my personal details
  As a valid OrangeHRM user
  I want to update personal details in my profile

  Background:
    Given I am logged into the OrangeHRM Application

  Scenario: Update Personal Details
    Given I navigate to the My Info section
    When I should see my personal information
    And I update the personal details with the following values:
      | FirstName    | MiddleName | LastName   | DateOfBirth  | Nationality  | MaritalStatus  | Gender  | EmployeeId  | OtherId | DriverLicenseNumber | LicenseExpiryDate |
      | John         | Luther     | Doe        | 2020-16-01   | Afghan       | Single         | Female  | 12345       | 6789    | ABC123              | 2025-01-01        |
    And I save the personal details
    Then the personal details should be updated successfully with the following values:
      | FirstName    | MiddleName | LastName   | DateOfBirth  | Nationality  | MaritalStatus  | Gender  | EmployeeId  | OtherId | DriverLicenseNumber | LicenseExpiryDate |
      | John         | Luther     | Doe        | 2020-16-01   | Afghan       | Single         | Female  | 12345       | 6789    | ABC123              | 2025-01-01        |

