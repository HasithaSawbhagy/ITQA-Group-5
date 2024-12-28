package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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

public class AddVacancyStepDef {
    private WebDriver driver;
    private AddVacancyPage addVacancyPage;

    @Before
    public void setup() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\jdk-18.0.2\\bin\\chromedriver-win64\\chromedriver.exe");
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
    }

    @Given("I am on the Recruitment page")
    public void iAmOnTheRecruitmentPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewRecruitmentModule");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isRecruitmentVisible = wait.until(driver -> driver.getPageSource().contains("Recruitment"));

        Assert.assertTrue(isRecruitmentVisible, "Recruitment page is not displayed!");
    }

    @When("I navigate to the Add Vacancy page")
    public void iNavigateToTheAddVacancyPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/recruitment/addJobVacancy");
        addVacancyPage = new AddVacancyPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isAddVacancyVisible = wait.until(driver -> driver.getPageSource().contains("Add Vacancy"));

        Assert.assertTrue(isAddVacancyVisible, "Failed to navigate to Add Vacancy page!");
    }

    @When("I fill out the vacancy details")
    public void iFillOutTheVacancyDetails(List<Map<String, String>> vacancyData) {
        Map<String, String> data = vacancyData.get(0); // Get the first (and only) data row
        addVacancyPage.fillVacancyDetails(
                data.get("VacancyName"),
                data.get("Description"),
                data.get("HiringManager"),
                data.get("Positions"),
                data.get("JobTitle")
        );
    }

    @When("I save the vacancy")
    public void iSaveTheVacancy() {
        addVacancyPage.saveVacancy();
    }

    @Then("I should see the \"Edit Vacancy\" page for the newly added vacancy")
    public void iShouldSeeTheEditVacancyPageForTheNewlyAddedVacancy() {
        Assert.assertTrue(addVacancyPage.isEditVacancyPageDisplayed(), "Edit Vacancy page is not displayed.");
    }
    /*public void theVacancyShouldBeAddedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/recruitment/viewJobVacancy/"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/recruitment/viewJobVacancy/"));
    }*/


}