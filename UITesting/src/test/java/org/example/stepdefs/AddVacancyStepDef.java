package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.AddVacancyPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddVacancyStepDef {
    private static final Logger log = LoggerFactory.getLogger(AddVacancyStepDef.class);
    private WebDriver driver;
    private AddVacancyPage addVacancyPage;

    @Before
    public void setup() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        log.info("setup done.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("browser closed.");
        }
    }

    @Given("I have logged in to the OrangeHRM Application")
    public void iHaveLoggedInToTheOrangeHRMApplication() {
        log.info("Navigating to login page and logging in.");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
        addVacancyPage = new AddVacancyPage(driver);
        log.info("login done and navigated to homepage.");
    }

    @Given("I navigate to the Recruitment page")
    public void iAmOnTheRecruitmentPage() {
        log.info("Navigating to recruitment page.");
        addVacancyPage.clickRecruitmentButton();
        log.info("Navigated to recruitment page.");
    }


    @When("I click the Vacancy tab")
    public void iClickTheVacancyTab() {
        log.info("Navigating to vacancies page.");
        addVacancyPage.clickVacanciesButton();
        log.info("Navigated to vacancies page.");
    }

    @When("I click the Add Vacancy button")
    public void iClickTheAddVacancyButton() {
        log.info("Navigating to add vacancy page.");
        addVacancyPage.clickAddButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isAddVacancyVisible = wait.until(driver -> driver.getPageSource().contains("Add Vacancy"));
        Assert.assertTrue(isAddVacancyVisible, "Failed to navigate to Add Vacancy page!");
        log.info("Navigated to add vacancy page.");

    }


    @When("I fill out the vacancy details")
    public void iFillOutTheVacancyDetails(List<Map<String, String>> vacancyData) {
        Map<String, String> data = vacancyData.get(0);
        log.info("Filling vacancy details.");
        addVacancyPage.fillVacancyDetails(
                data.get("VacancyName"),
                data.get("Description"),
                data.get("HiringManager"),
                data.get("Positions"),
                data.get("JobTitle")
        );
        log.info("Filled vacancy details.");
    }


    @When("I save the vacancy")
    public void iSaveTheVacancy() {
        log.info("Saving vacancy.");
        addVacancyPage.saveVacancy();
        log.info("Vacancy saved.");
    }
    @Then("I should see the \"Edit Vacancy\" page for the newly added vacancy")
    public void iShouldSeeTheEditVacancyPageForTheNewlyAddedVacancy() {
        log.info("Checking if Edit Vacancy page is displayed.");
        Assert.assertTrue(addVacancyPage.isEditVacancyPageDisplayed(), "Edit Vacancy page is not displayed.");
        log.info("Edit Vacancy page is displayed.");
    }

//

    @And("I fill out the vacancy details with existing {string} , {string} ,{string} , {string} and {string}")
    public void iFillOutTheVacancyDetailsWithExisting(String jobTitle, String vacancyName, String hiringManager, String positions, String description) {
        log.info("Filling vacancy details with existing data.");
        addVacancyPage.fillVacancyDetailsWithExisting(jobTitle, vacancyName, hiringManager, positions, description);
        log.info("Filled vacancy details with existing data.");
    }

    @When("I click the save button")
    public void iClickTheSaveButtonOnly() {
        log.info("Clicking the save button.");
        addVacancyPage.clickSaveButtonOnly();
        log.info("Clicked the save button.");
    }

    @Then("I should see an error message indicating the {string}")
    public void iShouldSeeAnErrorMessageIndicating(String errorMessage) {
        log.info("Checking if error message '{}' is displayed.", errorMessage);
        Assert.assertTrue(addVacancyPage.isErrorMessageDisplayed(errorMessage), "Error message is not displayed: " + errorMessage);
        log.info("Error message '{}' is displayed.", errorMessage);
    }



}