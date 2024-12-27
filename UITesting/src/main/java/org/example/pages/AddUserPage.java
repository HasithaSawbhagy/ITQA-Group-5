package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddUserPage extends BasePage {

    @FindBy(xpath = "//div[@class='oxd-select-text-input' and text()='-- Select --']")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameField;

    @FindBy(xpath = "//div[@class='oxd-select-text-input' and text()='Disabled']")
    private WebElement statusDropdown;

    @FindBy(xpath = "//input[@autocomplete='off']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@type='password' and @autocomplete='off']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type='password' and @autocomplete='off']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//p[contains(text(), 'Successfully Saved')]")
    private WebElement successMessage;

    public AddUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectUserRole(String role) {
        userRoleDropdown.click();
        // Logic to select the role from the dropdown
    }

    public void enterEmployeeName(String name) {
        employeeNameField.sendKeys(name);
    }

    public void selectStatus(String status) {
        statusDropdown.click();
        // Logic to select the status from the dropdown
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

    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }
}
