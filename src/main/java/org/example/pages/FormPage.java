package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPage extends BasePage {

// Locators for the claim page..........................................................................

@FindBy(xpath = "//label[text()='Event']/following::div[@class='oxd-select-text oxd-select-text--active']\n")
private WebElement eventDropdown;


@FindBy(xpath = "//label[text()='Currency']/following::div[@class='oxd-select-text oxd-select-text--active']\n")
private WebElement currencyDropdown;

@FindBy(xpath = "//label[text()='Remarks']/following::textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']\n")
private WebElement remarkField;

@FindBy(xpath = "//button[contains(text(),'Create') and @class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
private WebElement Create;

@FindBy(xpath = "//button[contains(text(),'Cancel') and @class='oxd-button oxd-button--medium oxd-button--ghost']")
private WebElement cancel;


    // Constructor.........................................................................................

    public FormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    // Reusable wait methods..................................................................................

    private WebElement waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }



//Actions..........................................................................................................

    public void selectEvent(String event) {
        waitForElementToBeClickable(eventDropdown).click();
        WebElement optionToSelect = waitForElementToBeVisible(
                By.xpath("//div[@role='option' and text()='" + event + "']")
        );
        optionToSelect.click();
    }

    public void selectCurrency(String currency) {
        waitForElementToBeClickable(currencyDropdown).click();
        WebElement optionToSelect = waitForElementToBeVisible(
                By.xpath("//div[@role='option' and text()='" + currency + "']")
        );
        optionToSelect.click();
    }

    public void enterRemarks(String remarks) {
        waitForElementToBeClickable(remarkField).sendKeys(remarks);
        // Remove suggestion logic if unnecessary.
    }

    public void clickCreateButton() {
        waitForElementToBeClickable(Create).click();
    }

    public void clickCancelButton() {
        waitForElementToBeClickable(cancel).click();
    }


//    public void selectEvent(String event) {
//        waitForElementToBeClickable(eventDropdown).click();
//        WebElement optionToSelect = waitForElementToBeVisible(
//                By.xpath("//div[@role='option' and span[text()='" + event + "']]")
//        );
//        optionToSelect.click();
//    }
//
//    public void selectCurrency(String currency) {
//        waitForElementToBeClickable(currencyDropdown).click();
//        WebElement optionToSelect = waitForElementToBeVisible(
//                By.xpath("//div[@role='option' and span[text()='" + currency + "']]")
//        );
//        optionToSelect.click();
//    }
//
//    public void enterRemarks(String remarks) {
//        remarkField.sendKeys(remarks);
//        WebElement suggestion = waitForElementToBeVisible(
//                By.xpath("//div[@role='option' and contains(.,'" + remarks + "')]")
//        );
//        suggestion.click();
//    }
//
//    public void clickCreateButton() {
//        waitForElementToBeClickable( Create  ).click();
//    }
//
//    public void clickCancelButton() {
//        waitForElementToBeClickable( cancel  ).click();
//    }

}