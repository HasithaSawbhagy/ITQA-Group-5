package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.AddUserPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class AddUserSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private AddUserPage addUserPage;

    @Before
    public void setup() {
        // Set the path to your chromedriver executable here
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am logged into the HRM system as Admin")
    public void iAmLoggedIntoTheHRMSystem() {
        // Open the HRM login page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Initialize the LoginPage object and log in
        loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
    }

    @When("I navigate to the \"View System Users\" page via the \"Admin\" option in the side navigation bar")
    public void iNavigateToTheAdminOption() {
        // Initialize the AddUserPage object
        addUserPage = new AddUserPage(driver);
        addUserPage.clickAdminOption();
    }

    @And("I click the \"Add\" button to open the \"Add User\" page")
    public void iNavigateToTheAddUser() {
        // Initialize the AddUserPage object
        addUserPage = new AddUserPage(driver);
        addUserPage.clickAddButton();
    }

    @And("I fill in the required fields with role {string}, status {string}, employee name {string}, username {string}, password {string}, and confirm password {string}")
    public void iFillInTheRequiredFields(String role, String status, String employeeName, String username, String password, String confirmPassword) {
        // Initialize the AddUserPage object
        addUserPage = new AddUserPage(driver);

        // Fill in the form fields
        addUserPage.selectUserRole(role);
        addUserPage.selectStatus(status);
        addUserPage.enterEmployeeName(employeeName);
        addUserPage.enterUsername(username);
        addUserPage.enterPassword(password);
        addUserPage.enterConfirmPassword(confirmPassword);
    }

    @And("I click the Save button")
    public void iClickTheButton() {
        addUserPage.clickSave();
    }

    @Then("I should see the View System Users page for the newly added user")
    public void theUserShouldBeCreatedSuccessfully() {
        // Part of the expected URL to check
        String expectedUrlPart = "viewSystemUsers";

        // Wait for the URL to contain the expected part
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isUrlCorrect = wait.until(ExpectedConditions.urlContains(expectedUrlPart));

        // Assert that the URL contains the expected part
        assertTrue("The URL does not contain the expected part: " + expectedUrlPart, isUrlCorrect);

        driver.quit();
    }

    @Then("I should see an error message \"Should be at least 5 characters\"")
    public void theErrorMessageDispalySuccessfully() {
        // Assert that the error message is displayed
        assertTrue("Error message 'Should be at least 5 characters' is not displayed", addUserPage.isErrorMessageDisplayed());

        driver.quit();
    }


}
