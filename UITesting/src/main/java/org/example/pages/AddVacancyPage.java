package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
    @FindBy(id = "jobTitle") // Ensure this matches the actual element's ID
    private WebElement jobTitleDropdown;

    @FindBy(id = "vacancyName") // Locator for "Vacancy Name"
    private WebElement vacancyNameField;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']") // Updated for "Hiring Manager" input field
    private WebElement hiringManagerField;

    @FindBy(id = "noOfPositions") // Updated for "Number of Positions" field
    private WebElement positionsField;

    @FindBy(id = "description") // Locator for "Description" field
    private WebElement descriptionField;

    @FindBy(xpath = "//label[contains(text(),'Active')]/preceding-sibling::input") // For Active checkbox
    private WebElement activeCheckbox;

    @FindBy(xpath = "//label[contains(text(),'Publish in RSS')]/preceding-sibling::input") // For RSS checkbox
    private WebElement rssCheckbox;

    @FindBy(xpath = "//button[@type='submit' and text()='Save']") // Locator for Save button
    private WebElement saveButton;

    // Constructor
    public AddVacancyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // 15 seconds for all waits
        PageFactory.initElements(driver, this);
    }

    // Method to add a vacancy
    public void addVacancy(String jobTitle, String vacancyName, String hiringManager, String positions,
                           String description, boolean isActive, boolean isPublish) {
        try {
            // Wait for the Job Title dropdown to be visible and select a value
            waitForElementToBeVisible(jobTitleDropdown);
            selectJobTitle(jobTitle);

            // Fill in the form fields
            vacancyNameField.sendKeys(vacancyName);
            hiringManagerField.sendKeys(hiringManager);
            positionsField.sendKeys(positions);
            descriptionField.sendKeys(description);

            // Handle Active checkbox
            if (isActive && !activeCheckbox.isSelected()) {
                activeCheckbox.click();
            } else if (!isActive && activeCheckbox.isSelected()) {
                activeCheckbox.click();
            }

            // Handle RSS checkbox
            if (isPublish && !rssCheckbox.isSelected()) {
                rssCheckbox.click();
            } else if (!isPublish && rssCheckbox.isSelected()) {
                rssCheckbox.click();
            }

            // Click Save
            waitForElementToBeClickable(saveButton);
            saveButton.click();

        } catch (Exception e) {
            System.err.println("Error while adding vacancy: " + e.getMessage());
        }
    }

    // Helper method to select a value from the Job Title dropdown
    private void selectJobTitle(String jobTitle) {
        try {
            Select select = new Select(jobTitleDropdown);
            select.selectByVisibleText(jobTitle); // Select by visible text
        } catch (Exception e) {
            System.err.println("Failed to select job title: " + e.getMessage());
        }
    }

    // Helper method to wait for an element to be visible
    private void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Helper method to wait for an element to be clickable
    private void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }



    public void saveVacancy() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Wait for the button to be clickable
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and normalize-space()='Save']")));

            // Click the button
            saveButton.click();
        } catch (TimeoutException e) {
            System.out.println("Save button not found or not clickable: " + e.getMessage());
            throw e;
        }
    }


}
