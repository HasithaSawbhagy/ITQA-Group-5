package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage extends BasePage{
    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "btnSave")
    private WebElement saveButton;

    @FindBy(xpath = "//h1[text()='Personal Details']")
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
