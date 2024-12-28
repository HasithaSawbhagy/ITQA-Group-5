package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import java.time.Duration;

public class AddVacancyPage extends BasePage {

    @FindBy(xpath = "//label[text()='Vacancy Name']/following::input[1]")
    private WebElement vacancyNameField;

    @FindBy(xpath = "//label[text()='Job Title']/following::div[1]")
    private WebElement jobTitleDropdown;

    private WebElement jobTitleOption;

    @FindBy(xpath = "//label[text()='Description']/following::textarea[1]")
    private WebElement descriptionField;

    @FindBy(xpath = "//label[text()='Hiring Manager']/following::div[1]/div/div/input")
    private WebElement hiringManagerInputField;

    private WebElement hiringManagerOption;

    @FindBy(xpath = "//label[text()='Number of Positions']/following::input[1]")
    private WebElement numberOfPositionsField;

    @FindBy(xpath = "//label[text()='Active']/following::span[contains(@class,'oxd-switch-input--active')]")
    private WebElement activeSwitch;

    @FindBy(xpath = "//label[text()='Publish in RSS Feed and Web Page']/following::span[contains(@class,'oxd-switch-input--active')]")
    private WebElement publishSwitch;

    @FindBy(xpath = "//button[text()=' Save ']")
    private WebElement saveButton;

    @FindBy(xpath = "//h6[text()='Edit Vacancy']")
    private WebElement editVacancyTitle;

    private WebDriver driver;


    public AddVacancyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void enterVacancyName(String vacancyName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(vacancyNameField));
        vacancyNameField.sendKeys(vacancyName);
    }

    public void selectJobTitle(String jobTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(jobTitleDropdown));
        jobTitleDropdown.click();
        String dynamicJobTitleOptionXpath = String.format("//div[contains(@class, 'oxd-select-dropdown')]//span[text()='%s']",jobTitle );
        try {
            jobTitleOption =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicJobTitleOptionXpath)));
            jobTitleOption.click();
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("Job title " + jobTitle + " not found " + e.getMessage());
        }catch(StaleElementReferenceException e){
            jobTitleOption =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicJobTitleOptionXpath)));
            jobTitleOption.click();
        }
    }

    public void enterDescription(String description) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(descriptionField));
        descriptionField.sendKeys(description);
    }

    public void enterHiringManager(String hiringManager) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(hiringManagerInputField));
        hiringManagerInputField.sendKeys(hiringManager);
        String dynamicHiringManagerOptionXpath = String.format("//div[contains(@class, 'oxd-autocomplete-dropdown')]//span[contains(text(),'%s')]", hiringManager);
        hiringManagerOption =  driver.findElement(By.xpath(dynamicHiringManagerOptionXpath));
        wait.until(ExpectedConditions.elementToBeClickable(hiringManagerOption));
        hiringManagerOption.click();
    }


    public void enterNumberOfPositions(String numberOfPositions) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(numberOfPositionsField));
        numberOfPositionsField.sendKeys(numberOfPositions);
    }


    public void activateActiveSwitch(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(activeSwitch));
        activeSwitch.click();
    }
    public void activatePublishSwitch(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(publishSwitch));
        publishSwitch.click();
    }

    public void saveVacancy() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(editVacancyTitle));
        //wait.until(ExpectedConditions.invisibilityOf(saveButton));
        //wait.until(ExpectedConditions.urlContains("/recruitment/addJobVacancy/"));
    }

    public void fillVacancyDetails(String vacancyName, String description, String hiringManager, String numberOfPositions, String jobTitle) {
        enterVacancyName(vacancyName);
        selectJobTitle(jobTitle);
        enterDescription(description);
        enterHiringManager(hiringManager);
        enterNumberOfPositions(numberOfPositions);
        // Assuming the switches are on by default, only click if we want them off
        // activateActiveSwitch();
        // activatePublishSwitch();
    }

    public boolean isEditVacancyPageDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(editVacancyTitle)).isDisplayed();
    }

}