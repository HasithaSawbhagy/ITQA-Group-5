package org.example.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.AddUserPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddUserStepDef {

    private WebDriver driver;
    private LoginPage loginPage;
    private AddUserPage addUserPage;

    @Given("I am logged into the OrangeHRM Application")
    public void iAmLoggedIntoTheOrangeHRMApplication() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        addUserPage = new AddUserPage(driver);
        loginPage.login("Admin", "admin123");
    }

    @When("I navigate to the Add User page")
    public void iNavigateToTheAddUserPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
    }

    @And("I fill in the Add User form with {string}, {string}, {string}, {string}, {string}, and {string}")
    public void iFillInTheAddUserForm(String role, String employeeName, String status, String username, String password, String confirmPassword) {
        addUserPage.selectUserRole(role);
        addUserPage.enterEmployeeName(employeeName);
        addUserPage.selectStatus(status);
        addUserPage.enterUsername(username);
        addUserPage.enterPassword(password);
        addUserPage.enterConfirmPassword(confirmPassword);
    }

    @Then("The user should be added successfully")
    public void theUserShouldBeAddedSuccessfully() {
        addUserPage.clickSaveButton();
        // Add assertions to verify the user is added successfully
        driver.quit();
    }
}
