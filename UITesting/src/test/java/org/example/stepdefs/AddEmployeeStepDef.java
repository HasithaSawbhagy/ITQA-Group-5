package org.example.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.AddEmployeePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEmployeeStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private AddEmployeePage addEmployeePage;

    @Given("I am logged into the OrangeHRM Application")
    public void iAmLoggedIntoTheOrangeHRMApplication() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
    }

    @When("I navigate to the Add Employee page")
    public void iNavigateToTheAddEmployeePage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
        addEmployeePage = new AddEmployeePage(driver);
    }

    @And("I enter employee details {string} {string} {string}")
    public void i_enter_employee_details(String firstName, String middleName, String lastName) {
        // Use the AddEmployeePage methods to interact with the form
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver); // Assuming you have created this page model
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.clickSave();
    }

    @Then("The employee should be added successfully")
    public void theEmployeeShouldBeAddedSuccessfully() {
        // Add assertions to verify successful addition of the employee, e.g., checking a success message.
        driver.quit();
    }
}
