package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddEmployeePage extends BasePage{
    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    //@FindBy(xpath = "//h6[text()='Personal Details']")
    //@FindBy(xpath = "//div[@class='oxd-text oxd-text--h6 orangehrm-main-title']//h6[text()='Personal Details']")
    //*[@id="app"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/h6
    @FindBy(css = "h6.oxd-text.oxd-text--h6.orangehrm-main-title")
    private WebElement personalDetailsHeader;
    private WebDriverWait wait;

    public AddEmployeePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void clickSaveButton() {
        WebElement saveButton = waitForElementToBeClickable(this.saveButton);
        saveButton.click();
    }

    public boolean isPersonalDetailsPageDisplayed() {
        WebElement header = waitForElementToBeVisible(this.personalDetailsHeader);
        return personalDetailsHeader.isDisplayed();
    }

    public void addEmployee(String firstName, String lastName) {
        enterFirstName(firstName);
        enterLastName(lastName);
        clickSaveButton();
    }
}
