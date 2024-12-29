package org.example.stepdefs;

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

    @Given("I am logged into the HRM system")
    public void iAmLoggedIntoTheHRMSystem() {
        // Set up the WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

        // Open the HRM login page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Initialize the LoginPage object and log in
        loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
    }

    @When("I create a new user with the role {string}, status {string}, employee name {string}, username {string}, password {string}, and confirm password {string}")
    public void iCreateANewUser(String role, String status, String employeeName, String username, String password, String confirmPassword) {
        // Navigate to the "Add User" page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");

        // Initialize the AddUserPage object
        addUserPage = new AddUserPage(driver);

        // Fill in the form to create a new user
        addUserPage.selectUserRole(role);
        addUserPage.selectStatus(status);
        addUserPage.enterEmployeeName(employeeName);
        addUserPage.enterUsername(username);
        addUserPage.enterPassword(password);
        addUserPage.enterConfirmPassword(confirmPassword);

        // Click the save button
        addUserPage.clickSave();
    }

    @Then("The user should be created successfully")
    public void theUserShouldBeCreatedSuccessfully() {
        // Expected URL
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";

        // Wait for the URL to change to the expected value
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isUrlCorrect = wait.until(ExpectedConditions.urlToBe(expectedUrl));

        // Assert the URL is correct
        assertTrue("The user was not redirected to the expected URL: " + expectedUrl, isUrlCorrect);

        // Close the browser
        driver.quit();
    }


}
