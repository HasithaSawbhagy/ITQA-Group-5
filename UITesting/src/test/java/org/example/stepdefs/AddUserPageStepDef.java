package org.example.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.AddUserPage;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AddUserPageStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private AddUserPage addUserPage;

    @Given("I am logged in as an admin")
    public void iAmLoggedInAsAnAdmin() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage = new LoginPage(driver);
        homePage = loginPage.login("Admin", "admin123");

        // Verify login success
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
    }

    @When("I navigate to the Add User page")
    public void iNavigateToTheAddUserPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
        addUserPage = new AddUserPage(driver);
    }

    @When("I fill out the user details")
    public void iFillOutTheUserDetails(io.cucumber.datatable.DataTable dataTable) {
        var userDetails = dataTable.asMaps(String.class, String.class).get(0);

        addUserPage.selectUserRole(userDetails.get("UserRole"));
        addUserPage.enterEmployeeName(userDetails.get("EmployeeName"));
        addUserPage.selectStatus(userDetails.get("Status"));
        addUserPage.enterUsername(userDetails.get("Username"));
        addUserPage.enterPassword(userDetails.get("Password"));
        addUserPage.enterConfirmPassword(userDetails.get("ConfirmPassword"));
    }

    @When("I save the new user")
    public void iSaveTheNewUser() {
        addUserPage.clickSaveButton();
    }

    @Then("the new user should be added successfully")
    public void theNewUserShouldBeAddedSuccessfully() {
        // Add a verification step to check for a success message
        Assert.assertTrue(addUserPage.isSuccessMessageDisplayed());
        driver.quit();
    }
}
