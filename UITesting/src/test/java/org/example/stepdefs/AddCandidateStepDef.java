package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.AddCandidatePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class AddCandidateStepDef {
    private WebDriver driver;
    private AddCandidatePage addCandidatePage;

    @Before
    public void setup() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I have logged in to OrangeHRM Application")
    public void iHaveLoggedInToTheOrangeHRMApplication() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
        addCandidatePage = new AddCandidatePage(driver);
    }
    @Given("I navigate to Recruitment page")
    public void iAmOnTheRecruitmentPage() {
        addCandidatePage.clickRecruitmentButton();
    }


    @When("I click the candidate tab")
    public void iClickTheCandidateTab() {
        addCandidatePage.clickcandidatesButton();
    }

    @When("I click the Add candidate button")
    public void iClickTheAddCandidateButton() {
        addCandidatePage.clickAddButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        boolean isAddVacancyVisible = wait.until(driver -> driver.getPageSource().contains("Add Candidate"));
        Assert.assertTrue(isAddVacancyVisible, "Failed to navigate to Add Candidate page!");

    }

    @When("I fill out the candidate details with")
    public void iFillOutTheCandidateDetails(List<Map<String, String>> candidateData) {
        Map<String, String> data = candidateData.get(0);
        addCandidatePage.fillCandidateDetails(
                data.get("firstName"),
                data.get("middleName"),
                data.get("lastName"),
                data.get("notes"),
                data.get("contact"),
                data.get("JobTitle"),
                data.get("email"),
                data.get("keywords")
        );
    }
    @When("I upload a resume")
    public void iUploadAResume(){
        String filePath = "C:\\Users\\ASUS\\Downloads\\test.pdf";
        addCandidatePage.uploadResume(filePath);
    }

    @When("I check the consent checkbox")
    public void iCheckTheConsentCheckbox() {
        addCandidatePage.clickConsentCheckbox();
    }

    @When("I save the candidate")
    public void iSaveTheCandidate() {
        addCandidatePage.saveCandidate();
    }

    @Then("I should see the error messages")
    public void iShouldSeeTheErrorMessages() {
        List<String> errorMessages = addCandidatePage.getErrorMessages();
        Assert.assertFalse(errorMessages.isEmpty(), "Error message is not displayed");
        for(String errorMessage : errorMessages){
            Assert.assertTrue(errorMessage.equals("Required") || errorMessage.equals("Expected format: admin@example.com"),
                    "Error message is not 'Required' or 'Expected format: admin@example.com': " + errorMessage);
        }
    }


    @Then("I should see the \"Edit candidate\" page for the newly added candidate")
    public void iShouldSeeTheEditCandidatePageForTheNewlyAddedCandidate() {
        Assert.assertTrue(addCandidatePage.isEditCandidatePageDisplayed(), "Edit Candidate page is not displayed.");
    }


    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage(){
        List<String> errorMessages = addCandidatePage.getErrorMessages();
        Assert.assertFalse(errorMessages.isEmpty(), "Error message is not displayed");
    }
}