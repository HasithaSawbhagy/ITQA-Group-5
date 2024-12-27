package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage extends BasePage {

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "middleName")
    private WebElement middleNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    private WebDriverWait wait;

    public AddEmployeePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait for element visibility
        PageFactory.initElements(driver, this);
    }

    private WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void enterFirstName(String firstName) {
        WebElement firstNameField = waitForElementToBeVisible(this.firstNameField);
        firstNameField.sendKeys(firstName);
    }

    public void enterMiddleName(String middleName) {
        WebElement middleNameField = waitForElementToBeVisible(this.middleNameField);
        middleNameField.sendKeys(middleName);
    }

    public void enterLastName(String lastName) {
        WebElement lastNameField = waitForElementToBeVisible(this.lastNameField);
        lastNameField.sendKeys(lastName);
    }

    public void clickSave() {
        WebElement saveButton = waitForElementToBeClickable(this.saveButton);
        saveButton.click();
    }
}
