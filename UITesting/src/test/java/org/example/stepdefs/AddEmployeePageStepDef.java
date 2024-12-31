package org.example.stepdefs;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.AddEmployeePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
public class AddEmployeePageStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private AddEmployeePage addEmployeePage;

    @Given("I am logged in to the OrangeHRM application with valid credentials")
    public void iAmLoggedInToTheOrangeHRMApplicationWithValidCredentials() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Chamudi\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
    }

    @When("I navigate to the \"Add Employee\" page")
    public void iNavigateToTheAddEmployeePage() {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
        addEmployeePage = new AddEmployeePage(driver);
    }

    @When("I fill in the required fields {string} and {string}")
    public void iFillInTheRequiredFields(String firstName, String lastName) {
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterLastName(lastName);
    }

    @When("I click the \"Save\" button")
    public void iClickTheSaveButton() {
        addEmployeePage.clickSave();
    }

    @Then("I should see the \"Personal Details\" page for the newly added employee")
    public void iShouldSeeThePersonalDetailsPageForTheNewlyAddedEmployee() {
        Assert.assertTrue(addEmployeePage.isPersonalDetailsPageDisplayed(), "Personal Details page is not displayed.");
        driver.quit();
    }
}
