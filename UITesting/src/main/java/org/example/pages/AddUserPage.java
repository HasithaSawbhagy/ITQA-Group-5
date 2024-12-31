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

public class AddUserPage extends BasePage{


    // Locators for the "Add User" page
    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminOption;

    @FindBy(xpath = "//button[@type='button' and contains(@class, 'oxd-button--secondary')]")
    private WebElement addButton;
    @FindBy(xpath = "//label[text()='User Role']/following::div[contains(@class, 'oxd-select-text-input') and text()='-- Select --']")
    private WebElement userRoleDropdown;
    @FindBy(xpath = "//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]")
    private WebElement statusDropdown;
    // Locators for the "Add User" page
    @FindBy(xpath = "//label[text()='Employee Name']/following::input[@placeholder='Type for hints...']")
    private WebElement employeeNameField;
    @FindBy(xpath = "//label[text()='Username']/following::input[@class='oxd-input oxd-input--active' and @autocomplete='off']")
    private WebElement usernameField;
    @FindBy(xpath = "//label[text()='Password']/following::input[@type='password' and @autocomplete='off'][1]")
    private WebElement passwordField;
    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[@type='password' and @autocomplete='off'][1]")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Constructor
    public AddUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Method to click on the Admin option
    public void clickAdminOption() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the admin option is clickable
        wait.until(ExpectedConditions.elementToBeClickable(adminOption));

        // Click the admin option
        adminOption.click();
    }

    // Method to click on the Add button
    public void clickAddButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the Add button is clickable
        wait.until(ExpectedConditions.elementToBeClickable(addButton));

        // Click the Add button
        addButton.click();
    }


    // Method to select a user role
    public void selectUserRole(String role) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the dropdown is clickable and then click it to open
        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();

        // Wait until the dropdown options container is visible
        WebElement roleOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//div[@role='option' and contains(.,'" + role + "')]")
        ));

        // Click the desired option
        roleOption.click();
    }

    public void selectStatus(String status) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the dropdown is clickable and then click it to open
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();

        // Wait until the dropdown options container is visible
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='option' and span[text()='" + status + "']]")
        ));

        // Click the desired option
        optionToSelect.click();
    }

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the username field to be visible
        wait.until(ExpectedConditions.visibilityOf(usernameField));

        // Input the username
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the password field to be visible
        wait.until(ExpectedConditions.visibilityOf(passwordField));

        // Input the password
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the password field to be visible
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordField));

        // Input the password
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the save button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));

        // Click the save button
        saveButton.click();
    }
}
