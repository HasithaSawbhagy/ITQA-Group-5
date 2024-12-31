
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

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(css = "h6.oxd-text.oxd-text--h6.orangehrm-main-title")
    private WebElement personalDetailsHeader;


    private WebDriverWait wait;

    public AddEmployeePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // 10 seconds wait for element visibility
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

    public void enterLastName(String lastName) {
        WebElement lastNameField = waitForElementToBeVisible(this.lastNameField);
        lastNameField.sendKeys(lastName);
    }

    public void clickSave() {
        WebElement saveButton = waitForElementToBeClickable(this.saveButton);
        saveButton.click();
    }

    public boolean isPersonalDetailsPageDisplayed() {
        WebElement header = waitForElementToBeVisible(this.personalDetailsHeader);
        return personalDetailsHeader.isDisplayed();
    }
}