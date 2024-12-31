package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddUserPage extends BasePage {

    // Locators for the "Add User" page
    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminOption;

    @FindBy(xpath = "//button[@type='button' and contains(@class, 'oxd-button--secondary')]")
    private WebElement addButton;

    @FindBy(xpath = "//label[text()='User Role']/following::div[contains(@class, 'oxd-select-text-input') and text()='-- Select --']")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]")
    private WebElement statusDropdown;

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

    // Locator for the error message
    @FindBy(xpath = "//span[contains(@class, 'oxd-input-field-error-message') and text()='Should be at least 5 characters']")
    private WebElement errorMessage;

    // Constructor
    public AddUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Reusable wait methods
    private WebElement waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Actions
    public void clickAdminOption() {
        waitForElementToBeClickable(adminOption).click();
    }

    public void clickAddButton() {
        waitForElementToBeClickable(addButton).click();
    }

    public void selectUserRole(String role) {
        waitForElementToBeClickable(userRoleDropdown).click();
        WebElement roleOption = waitForElementToBeVisible(
                By.xpath(".//div[@role='option' and contains(.,'" + role + "')]")
        );
        roleOption.click();
    }

    public void selectStatus(String status) {
        waitForElementToBeClickable(statusDropdown).click();
        WebElement optionToSelect = waitForElementToBeVisible(
                By.xpath("//div[@role='option' and span[text()='" + status + "']]")
        );
        optionToSelect.click();
    }

    public void enterEmployeeName(String employeeName) {
        employeeNameField.sendKeys(employeeName);
        WebElement suggestion = waitForElementToBeVisible(
                By.xpath("//div[@role='option' and contains(.,'" + employeeName + "')]")
        );
        suggestion.click();
    }

    public void enterUsername(String username) {
        waitForElementToBeClickable(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForElementToBeClickable(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        waitForElementToBeClickable(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void clickSave() {
        waitForElementToBeClickable(saveButton).click();
    }

    // Method to check if the error message is displayed
    public boolean isErrorMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false; // Return false if the error message is not found or not visible
        }
    }
}
