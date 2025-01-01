package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.AddCandidatePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.*;
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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hasit\\Downloads\\chromedriver-win64\\chromedriver.exe");
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

    @Given("I have logged in to the OrangeHRM Application")
    public void iHaveLoggedInToTheOrangeHRMApplication() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
        addCandidatePage = new AddCandidatePage(driver);
    }
    @Given("I navigate to the Recruitment page")
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


    @When("I fill out the candidate details")
    public void iFillOutTheCandidateDetails(List<Map<String, String>> vacancyData) {
        Map<String, String> data = vacancyData.get(0); // Get the first (and only) data row
        addCandidatePage.fillCandidateDetails(
                data.get("firstName"),
                data.get("middleName"),
                data.get("lastName"),
                data.get("notes"),
                data.get("contact"),
                data.get("JobTitle"),
                data.get("email")

        );
    }

    @When("I save the candidate")
    public void iSaveTheCandidate() {
        addCandidatePage.saveCandidate();
    }


    @Then("I should see the \"Edit candidate\" page for the newly added candidate")
    public void iShouldSeeTheEditCandidatePageForTheNewlyAddedCandidate() {
        Assert.assertTrue(addCandidatePage.isEditCandidatePageDisplayed(), "Edit Candidate page is not displayed.");
    }
}