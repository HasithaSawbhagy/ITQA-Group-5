package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddVacancyPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for the form fields
    @FindBy(id = "jobTitle") // Update with the actual locator if incorrect
    private WebElement jobTitleDropdown;

    @FindBy(id = "vacancyName")
    private WebElement vacancyNameField;

    @FindBy(id = "hiringManager")
    private WebElement hiringManagerField;

    @FindBy(id = "positions")
    private WebElement positionsField;

    @FindBy(id = "description")
    private WebElement descriptionField;

    @FindBy(id = "activeCheckbox")
    private WebElement activeCheckbox;

    @FindBy(id = "rssCheckbox")
    private WebElement rssCheckbox;

    @FindBy(id = "saveButton")
    private WebElement saveButton;

    public AddVacancyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addVacancy(String jobTitle, String vacancyName, String hiringManager, String positions,
                           String description, boolean isActive, boolean isPublish) {
        // Wait for the dropdown to be visible
        waitForElementToBeVisible(jobTitleDropdown);
        selectJobTitle(jobTitle);

        vacancyNameField.sendKeys(vacancyName);
        hiringManagerField.sendKeys(hiringManager);
        positionsField.sendKeys(positions);
        descriptionField.sendKeys(description);

        if (isActive && !activeCheckbox.isSelected()) {
            activeCheckbox.click();
        }

        if (isPublish && !rssCheckbox.isSelected()) {
            rssCheckbox.click();
        }

        saveButton.click();
    }

    private void selectJobTitle(String jobTitle) {
        Select select = new Select(jobTitleDropdown);
        select.selectByVisibleText(jobTitle);
    }

    private void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
