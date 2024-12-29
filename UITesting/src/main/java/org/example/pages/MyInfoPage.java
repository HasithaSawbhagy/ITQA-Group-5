package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyInfoPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[contains(@href, 'viewMyDetails')]")
    private WebElement myInfoTab;

    @FindBy(xpath = "//h6[contains(@class, 'oxd-text--h6') and text()='Personal Details']")
    private WebElement personalDetailsHeader;

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "middleName")
    private WebElement middleNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//label[normalize-space(text())='Date of Birth']/following::input[1]")
    private WebElement dateOfBirthField;

    @FindBy(xpath = "//label[text()='Nationality']/following::div[1]")
    private WebElement nationalityDropdown;

    private WebElement nationalityOption;

    @FindBy(xpath = "//label[text()='Marital Status']/following::div[1]")
    private WebElement maritalStatusDropdown;

    private WebElement maritalStatusOption;

    @FindBy(xpath = "//input[@value='1']") // Male radio button
    private WebElement maleGenderOption;

    @FindBy(xpath = "//input[@value='2']") // Female radio button
    private WebElement femaleGenderOption;

    @FindBy(xpath = "//label[normalize-space(text())='Employee Id']/following::input[1]")
    private WebElement employeeIdField;

    @FindBy(xpath = "//label[normalize-space(text())='Other Id']/following::input[1]")
    private WebElement otherIdField;

    @FindBy(xpath = "//label[text()=\"Driver's License Number\"]/following::input[1]")
    private WebElement driverLicenseNumberField;

    @FindBy(xpath = "//label[normalize-space(text())='License Expiry Date']/following::input[1]")
    private WebElement licenseExpiryDateField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

//    @FindBy(xpath = "//div[contains(@class, 'oxd-toast-content') and contains(text(), 'Successfully Updated')]")
//    private WebElement successMessage;

    @FindBy(xpath = "//span[contains(@class, 'oxd-input-field-error-message')]")
    private WebElement errorMessage;


    // Constructor
    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // 15 seconds for all waits
        PageFactory.initElements(driver, this);
    }

    public void navigateToMyInfo() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(myInfoTab));
        myInfoTab.click();
    }

    public boolean isPersonalDetailsHeaderDisplayed() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement personalDetailsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(@class, 'oxd-text--h6') and text()='Personal Details']")));
        return personalDetailsHeader.isDisplayed();
    }

    private void clearAndEnterText(WebElement element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        // Select all text in the field
        element.sendKeys(Keys.CONTROL + "a");  // Selects all text in the input field
        element.sendKeys(Keys.DELETE);         // Clears the selected text
        element.sendKeys(text);
    }

    public void selectOptionText(WebElement optionelement, WebElement dropdownelement, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dropdownelement));
        dropdownelement.click();
        String dynamicNationalityOptionXpath = String.format("//div[contains(@class, 'oxd-select-dropdown')]//span[text()='%s']",text );
        try {
            optionelement =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicNationalityOptionXpath)));
            optionelement.click();
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("Option " + text + " not found " + e.getMessage());
        }catch(StaleElementReferenceException e){
            optionelement =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicNationalityOptionXpath)));
            optionelement.click();
        }
    }


    public void updateDetails(String firstName,  String middleName, String lastName, String dateOfBirth, String nationality, String maritalStatus, String gender, String employeeId, String otherId, String driverLicenseNumber, String licenseExpiryDate) {
        try {
            clearAndEnterText(firstNameField, firstName);
            clearAndEnterText(middleNameField, middleName);
            clearAndEnterText(lastNameField, lastName);
            clearAndEnterText(employeeIdField, employeeId);
            clearAndEnterText(otherIdField, otherId);
            clearAndEnterText(driverLicenseNumberField, driverLicenseNumber);
            clearAndEnterText(licenseExpiryDateField, licenseExpiryDate);

            WebElement dateOfBirthInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[normalize-space(text())='Date of Birth']/following::input[1]")
            ));
            clearAndEnterText(dateOfBirthField, dateOfBirth);

            WebElement nationalityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[text()='Nationality']/following::div[1]")
            ));
            selectOptionText(nationalityOption, nationalityDropdown, nationality);

            WebElement maritalStatusInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[text()='Marital Status']/following::div[1]")
            ));
            selectOptionText(maritalStatusOption, maritalStatusDropdown, maritalStatus);

            // Select gender radio button with additional visibility and interactability checks
            if (gender.equalsIgnoreCase("Male")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", maleGenderOption);
            } else if (gender.equalsIgnoreCase("Female")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", femaleGenderOption);
            }

            // Wait and click the save button
            waitForElementToBeClickable(saveButton);
            saveButton.click();

        } catch (Exception e) {
            System.err.println("Error while updating details: " + e.getMessage());
        }
    }

    // Helper method to wait for an element to be clickable
    private void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickSaveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));

        saveButton.click();
    }

    // Method to retrieve updated personal details
    public String getFirstName() {
        return firstNameField.getAttribute("value");
    }

    public String getMiddleName() {
        return middleNameField.getAttribute("value");
    }

    public String getLastName() {
        return lastNameField.getAttribute("value");
    }

    public String getDateOfBirth() {
        return dateOfBirthField.getAttribute("value");
    }

    public String getEmployeeId() {
        return employeeIdField.getAttribute("value");
    }

    public String getOtherId() {
        return otherIdField.getAttribute("value");
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumberField.getAttribute("value");
    }

    public String getLicenseExpiryDate() {
        return licenseExpiryDateField.getAttribute("value");
    }

    public String getNationality() {
        // Assuming the selected value is displayed in a div with class 'oxd-select-text-input'
        WebElement selectedNationality = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Nationality']/following::div[contains(@class, 'oxd-select-text-input')]")
        ));
        return selectedNationality.getText();
    }

    public String getMaritalStatus() {
        // Adjust XPath to match the selected marital status value's container
        WebElement selectedMaritalStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Marital Status']/following::div[contains(@class, 'oxd-select-text-input')]")
        ));
        return selectedMaritalStatus.getText();
    }

    public String getGender() {
        if (maleGenderOption.isSelected()) {
            return "Male";
        } else if (femaleGenderOption.isSelected()) {
            return "Female";
        } else {
            return "Not Selected";
        }
    }

}
