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
import java.util.List;

public class AddCandidatePage extends BasePage {

    private WebDriverWait wait;
    private WebDriver driver;

    @FindBy(xpath = "//label[text()='Full Name']/following::input[1]")
    private WebElement candidateFirstNameField;
    @FindBy(xpath = "//label[text()='Full Name']/following::input[2]")
    private WebElement candidateMiddleNameField;
    @FindBy(xpath = "//label[text()='Full Name']/following::input[3]")
    private WebElement candidateLastNameField;

    @FindBy(xpath = "//label[text()='Email']/following::input[1]")
    private WebElement candidateEmailField;

    @FindBy(xpath = "//label[text()='Vacancy']/following::div[1]")
    private WebElement vacancyDropdown;

    private WebElement jobTitleOption;

    @FindBy(xpath = "//label[text()='Notes']/following::textarea[1]")
    private WebElement notesField;


    @FindBy(xpath = "//label[text()='Contact Number']/following::input[1]")
    private WebElement contactInputField;

    @FindBy(xpath = "//input[@placeholder='Enter comma seperated words...']")
    private WebElement keywordsField;


    @FindBy(xpath = "//button[text()=' Save ']")
    private WebElement saveButton;

    @FindBy(xpath = "//h6[text()='Application Stage']")
    private WebElement editCandidateTitle;

    @FindBy(xpath = "//span[text()='Recruitment']")
    private WebElement recruitmentButton;
    @FindBy(xpath = "//a[text()='Candidates']")
    private WebElement candidatesButton;
    @FindBy(xpath = "//button[text()=' Add ']")
    private WebElement addButton;

    @FindBy(xpath = "//span[contains(@class, 'oxd-input-field-error-message')]")
    private List<WebElement> errorMessages;

    // New elements
    @FindBy(xpath = "//div[contains(@class, 'oxd-file-button')]")
    private WebElement browseButton;
    //Removed fileInput element


    public AddCandidatePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void clickRecruitmentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(recruitmentButton));
        recruitmentButton.click();
    }
    public void clickcandidatesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(candidatesButton));
        candidatesButton.click();
    }
    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(candidateFirstNameField));
        candidateFirstNameField.sendKeys(firstName);
    }

    public void enterMiddleName(String middleName) {
        wait.until(ExpectedConditions.visibilityOf(candidateMiddleNameField));
        candidateMiddleNameField.sendKeys(middleName);
    }
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(candidateLastNameField));
        candidateLastNameField.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(candidateEmailField));
        candidateEmailField.sendKeys(email);
    }


    public void selectJobTitle(String jobTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(vacancyDropdown));
        vacancyDropdown.click();
        String dynamicJobTitleOptionXpath = String.format("//div[contains(@class, 'oxd-select-dropdown')]//span[text()='%s']", jobTitle);
        try {
            jobTitleOption =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicJobTitleOptionXpath)));
            jobTitleOption.click();
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("Job title " + jobTitle + " not found " + e.getMessage());
        }catch(StaleElementReferenceException e){
            jobTitleOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicJobTitleOptionXpath)));
            jobTitleOption.click();
        }
    }

    public void enterNote(String notes) {
        wait.until(ExpectedConditions.visibilityOf(notesField));
        notesField.sendKeys(notes);
    }



    public void enterContactNumber(String contact) {
        wait.until(ExpectedConditions.visibilityOf(contactInputField));
        contactInputField.sendKeys(contact);
    }

    public void enterKeywords(String keywords) {
        wait.until(ExpectedConditions.visibilityOf(keywordsField));
        keywordsField.sendKeys(keywords);
    }
    // Method to upload a file
    public void uploadResume(String filePath) {
        wait.until(ExpectedConditions.elementToBeClickable(browseButton));
        browseButton.click();
        // Using AutoIt or Robot class to select the file from the dialog
        try {
            Thread.sleep(1000); //giving some time for dialog to load, might not needed if the PC is fast
            java.awt.Robot robot = new java.awt.Robot();

            // Simulate pressing Ctrl+L (to focus on the address bar)
            robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
            robot.keyPress(java.awt.event.KeyEvent.VK_L);
            robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
            robot.keyRelease(java.awt.event.KeyEvent.VK_L);


            // Simulate typing the file path
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new java.awt.datatransfer.StringSelection(filePath), null);
            robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
            robot.keyPress(java.awt.event.KeyEvent.VK_V);
            robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
            robot.keyRelease(java.awt.event.KeyEvent.VK_V);
            Thread.sleep(500);
            robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
            robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);

        } catch (Exception e) {
            System.out.println("Exception occurred : " + e);
        }
    }


    public void saveCandidate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(editCandidateTitle));
    }

    public void fillCandidateDetails(String firstName, String middleName, String lastName, String notes, String contact, String jobTitle, String email, String keywords) {
        enterFirstName(firstName);
        enterMiddleName(middleName);
        enterLastName(lastName);
        selectJobTitle(jobTitle);
        enterNote(notes);
        enterContactNumber(contact);
        enterEmail(email);
        enterKeywords(keywords);
    }

    public boolean isEditCandidatePageDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(editCandidateTitle)).isDisplayed();
    }

    // Fetch all error messages displayed
    public List<String> getErrorMessages() {
        wait.until(ExpectedConditions.visibilityOfAllElements(errorMessages));
        return errorMessages.stream().map(WebElement::getText).toList();
    }
}