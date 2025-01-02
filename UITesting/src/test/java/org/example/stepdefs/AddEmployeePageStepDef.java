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

    @Given("I am logged in to the OrangeHRM application as an Admin")
    public void iAmLoggedInToTheOrangeHRMApplicationWithValidCredentials() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Chamudi\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
    }

    @When("I click on the \"PIM\" option in the side navigation bar")
    public void iClickOnThePIMOptionInTheSideNavigationBar() {
        addEmployeePage = new AddEmployeePage(driver); // Initialize AddEmployeePage
        addEmployeePage.clickPIMOption();
    }

    @When("I select \"Add Employee\" option in top navigation bar")
    public void iNavigateToTheAddEmployeePage() {
        addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.clickAddEmployeeOption();
    }

    @When("I fill in the required fields with first name {string} and last name {string}")
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
    @Then("I should see the \"Required\" message under the First Name field")
    public void iShouldSeeTheRequiredMessageUnderFirstNameField() {
        Assert.assertTrue(addEmployeePage.isFirstNameRequiredMessageDisplayed(), "Required message under First Name is not displayed.");
        driver.quit();
    }

}
