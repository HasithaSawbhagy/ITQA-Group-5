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

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//label[normalize-space(text())='Date of Birth']/following::input[1]")
    private WebElement dateOfBirthField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class, 'oxd-toast-content') and contains(text(), 'Successfully Updated')]")
    private WebElement successMessage;

    // Constructor
    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 15 seconds for all waits
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
    // Method to add a vacancy
    public void updateDetails(String firstName, String lastName, String dateOfBirth) {
        try {
            // Wait for the first name field to be clickable
            clearAndEnterText(firstNameField, firstName);

            // Wait for the last name field to be clickable
            clearAndEnterText(lastNameField, lastName);

            // Handle Date of Birth
            WebElement dateOfBirthInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[normalize-space(text())='Date of Birth']/following::input[1]")
            ));

            // Wait for the date of birth field to be clickable
            clearAndEnterText(dateOfBirthField, dateOfBirth);

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

    public String getLastName() {
        return lastNameField.getAttribute("value");
    }

    public String getDateOfBirth() {
        return dateOfBirthField.getAttribute("value");
    }



}
