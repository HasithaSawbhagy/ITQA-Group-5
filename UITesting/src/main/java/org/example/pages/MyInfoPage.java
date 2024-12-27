package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyInfoPage extends BasePage {

    @FindBy(xpath = "//a[contains(@href, 'viewMyDetails')]")
    private WebElement myInfoTab;

    @FindBy(xpath = "//h6[contains(@class, 'oxd-text--h6') and text()='Personal Details']")
    private WebElement personalDetailsHeader;

    public MyInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToMyInfo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(myInfoTab));
        myInfoTab.click();
    }

    public boolean isPersonalDetailsHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement personalDetailsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(@class, 'oxd-text--h6') and text()='Personal Details']")));
        return personalDetailsHeader.isDisplayed();
    }
}
