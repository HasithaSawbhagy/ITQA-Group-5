package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage extends BasePage{
    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//h6[text()='Personal Details']")
    private WebElement personalDetailsHeader;


    public AddEmployeePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public boolean isPersonalDetailsPageDisplayed() {
        return personalDetailsHeader.isDisplayed();
    }

    public void addEmployee(String firstName, String lastName) {
        enterFirstName(firstName);
        enterLastName(lastName);
        clickSaveButton();
    }
}
