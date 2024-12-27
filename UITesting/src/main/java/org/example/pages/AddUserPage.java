package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddUserPage extends BasePage {

    @FindBy(xpath = "//label[text()='User Role']/following-sibling::div//div[contains(@class, 'oxd-select-text')]")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//label[text()='Employee Name']/following-sibling::div//input")
    private WebElement employeeNameField;

    @FindBy(xpath = "//label[text()='Status']/following-sibling::div//div[contains(@class, 'oxd-select-text')]")
    private WebElement statusDropdown;

    @FindBy(xpath = "//label[text()='Username']/following-sibling::div//input")
    private WebElement usernameField;

    @FindBy(xpath = "//label[text()='Password']/following-sibling::div//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/following-sibling::div//input[@type='password']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    public AddUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectUserRole(String role) {
        userRoleDropdown.click();
        // Add logic to select role from the dropdown
    }

    public void enterEmployeeName(String name) {
        employeeNameField.sendKeys(name);
    }

    public void selectStatus(String status) {
        statusDropdown.click();
        // Add logic to select status from the dropdown
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickSaveButton() {
        saveButton.click();
    }
}
