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
import org.openqa.selenium.TimeoutException;
import java.time.Duration;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddVacancyPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(AddVacancyPage.class);
    private WebDriverWait wait;
    private WebDriver driver;

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

    @FindBy(xpath = "//span[text()='Recruitment']")
    private WebElement recruitmentButton;

    @FindBy(xpath = "//a[text()='Vacancies']")
    private WebElement vacanciesButton;

    @FindBy(xpath = "//button[text()=' Add ']")
    private WebElement addButton;

    @FindBy(xpath = "//span[contains(@class, 'oxd-input-field-error-message')]")
    private List<WebElement> errorMessages;
    public AddVacancyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }
    public void clickRecruitmentButton() {
        log.info("Clicking Recruitment button.");
        wait.until(ExpectedConditions.elementToBeClickable(recruitmentButton));
        recruitmentButton.click();
        log.info("Clicked Recruitment button.");
    }
    public void clickVacanciesButton() {
        log.info("Clicking Vacancies button.");
        wait.until(ExpectedConditions.elementToBeClickable(vacanciesButton));
        vacanciesButton.click();
        log.info("Clicked Vacancies button.");
    }
    public void clickAddButton() {
        log.info("Clicking add button.");
        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
        log.info("Clicked add button.");
    }
    public void enterVacancyName(String vacancyName) {
        log.info("Entering vacancy name: " + vacancyName);
        wait.until(ExpectedConditions.visibilityOf(vacancyNameField));
        vacancyNameField.sendKeys(vacancyName);
        log.info("Entered vacancy name: " + vacancyName);
    }

    public void selectJobTitle(String jobTitle) {
        log.info("Selecting job title: " + jobTitle);
        if (jobTitle != null && !jobTitle.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(jobTitleDropdown));
            jobTitleDropdown.click();
            String dynamicJobTitleOptionXpath = String.format("//div[contains(@class, 'oxd-select-dropdown')]//span[text()='%s']", jobTitle);
            try {
                jobTitleOption =   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dynamicJobTitleOptionXpath)));
                jobTitleOption.click();
                log.info("Selected job title: " + jobTitle);
            }catch (TimeoutException e) {
                log.error("Timeout Exception for job title: " + jobTitle, e);
                throw new TimeoutException("Timeout Exception for job title: "+ jobTitle + e.getMessage());
            } catch(NoSuchElementException e){
                log.error("No such element exception for job title: "+jobTitle, e);
                throw new NoSuchElementException("Job title " + jobTitle + " not found " + e.getMessage());
            } catch(StaleElementReferenceException e){
                log.warn("Stale element reference exception for job title " + jobTitle + ", retrying.", e);
                dynamicJobTitleOptionXpath = String.format("//div[contains(@class, 'oxd-select-dropdown')]//span[text()='%s']", jobTitle);
                jobTitleOption =   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dynamicJobTitleOptionXpath)));
                jobTitleOption.click();
                log.info("Selected job title: " + jobTitle + " after retry.");
            }
        } else {
            log.info("Job title is empty, skipping selection.");
        }
    }

    public void enterDescription(String description) {
        log.info("Entering description: " + description);
        wait.until(ExpectedConditions.visibilityOf(descriptionField));
        descriptionField.sendKeys(description);
        log.info("Entered description: " + description);
    }
    public void enterHiringManager(String hiringManager) {
        log.info("Entering hiring manager: " + hiringManager);
        wait.until(ExpectedConditions.visibilityOf(hiringManagerInputField));
        hiringManagerInputField.sendKeys(hiringManager);
        String dynamicHiringManagerOptionXpath = String.format("//div[contains(@class, 'oxd-autocomplete-dropdown')]//span[contains(text(),'%s')]", hiringManager);
        hiringManagerOption =  driver.findElement(By.xpath(dynamicHiringManagerOptionXpath));
        wait.until(ExpectedConditions.elementToBeClickable(hiringManagerOption));
        hiringManagerOption.click();
        log.info("Entered and selected hiring manager: " + hiringManager);
    }
    public void enterNumberOfPositions(String numberOfPositions) {
        log.info("Entering number of positions: " + numberOfPositions);
        wait.until(ExpectedConditions.visibilityOf(numberOfPositionsField));
        numberOfPositionsField.sendKeys(numberOfPositions);
        log.info("Entered number of positions: " + numberOfPositions);
    }
    public void activateActiveSwitch() {
        log.info("Activating active switch.");
        wait.until(ExpectedConditions.elementToBeClickable(activeSwitch));
        activeSwitch.click();
        log.info("Activated active switch.");
    }
    public void activatePublishSwitch() {
        log.info("Activating publish switch.");
        wait.until(ExpectedConditions.elementToBeClickable(publishSwitch));
        publishSwitch.click();
        log.info("Activated publish switch.");
    }

    public void saveVacancy() {
        log.info("Clicking save button.");
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(editVacancyTitle));
        log.info("Clicked save button and waiting for Edit Vacancy Page.");
    }
    public void fillVacancyDetails(String vacancyName, String description, String hiringManager, String numberOfPositions, String jobTitle) {
        enterVacancyName(vacancyName);
        selectJobTitle(jobTitle);
        enterDescription(description);
        enterHiringManager(hiringManager);
        enterNumberOfPositions(numberOfPositions);
    }
    public boolean isEditVacancyPageDisplayed(){
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h6[text()='Edit Vacancy']")));
            log.info("Edit vacancy page is displayed.");
            return true;
        }catch (TimeoutException e){
            log.error("Timeout Exception for Edit Vacancy element.", e);
            return false;
        }
    }
    public void addInvalidDetails(String vacancyName, String jobTitle, String hiringManager) {
        enterVacancyName(vacancyName);
        if(jobTitle != null && !jobTitle.isEmpty()){
            selectJobTitle(jobTitle);
        }
        enterHiringManager(hiringManager);
    }
    // Fetch all error messages displayed
    public List<String> getErrorMessages() {
        wait.until(ExpectedConditions.visibilityOfAllElements(errorMessages));
        return errorMessages.stream().map(WebElement::getText).toList();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }
}