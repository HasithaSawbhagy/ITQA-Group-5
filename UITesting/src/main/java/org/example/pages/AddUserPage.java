package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddUserPage {
    private WebDriver driver;

    // Locators for the "Add User" page
    @FindBy(xpath = "//div[@class='oxd-select-wrapper']//div[contains(@class, 'oxd-select-text-input') and text()='-- Select --']")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]")
    private WebElement statusDropdown;

    // Locators for the "Add User" page
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameField;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active' and @autocomplete='off']")
    private WebElement usernameField;

    @FindBy(xpath = "//label[text()='Password']/following::input[@type='password' and @autocomplete='off'][1]")
    private WebElement passwordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[@type='password' and @autocomplete='off'][1]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Constructor
    public AddUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to select a user role
    // Method to select a user role
    public void selectUserRole(String role) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the dropdown is clickable and then click it to open
        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();

        // Wait until the dropdown options container is visible
        WebElement dropdownContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'oxd-select-dropdown') and contains(@class, '--positon-bottom')]")
        ));

        // Locate the specific role option within the dropdown
        WebElement roleOption = dropdownContainer.findElement(
                By.xpath(".//div[@role='option' and contains(.,'" + role + "')]")
        );

        // Ensure the option is visible and click it
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", roleOption);
        wait.until(ExpectedConditions.elementToBeClickable(roleOption)).click();
    }

    public void selectStatus(String status) {
        // Click the Status dropdown to open it
        statusDropdown.click();

        // Wait for the dropdown options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='option' and span[text()='" + status + "']]")
        ));

        // Click the desired option
        optionToSelect.click();
    }


    // Method to enter employee name
    // Method to enter employee name
    public void enterEmployeeName(String employeeName) {
        // Input the employee name into the text box
        employeeNameField.sendKeys(employeeName);

        // Wait for the autocomplete suggestions to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='option' and contains(.,'" + employeeName + "')]")
        ));

        // Click the matching suggestion
        suggestion.click();
    }

    // Method to enter username
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    // Method to enter password
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    // Method to enter confirm password
    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }

    // Method to click the save button
    public void clickSave() {
        saveButton.click();
    }
}
