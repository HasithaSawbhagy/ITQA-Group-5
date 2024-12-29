package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.MyInfoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MyInfoPageStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private MyInfoPage myInfoPage;

    private HomePage homePage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\jdk-18.0.2\\bin\\chromedriver.exe");
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

    @Given("I am logged into the OrangeHRM Application")
    public void iAmLoggedIntoTheOrangeHRMApplication() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        homePage = loginPage.login("Admin", "admin123");

        // Verify login success
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
    }

    @Given("I navigate to the My Info section")
    public void iNavigateToTheMyInfoSection() {
        myInfoPage = new MyInfoPage(driver);
        myInfoPage.navigateToMyInfo();
    }

    @When("I should see my personal information")
    public void iShouldSeeMyPersonalInformation() {
        Assert.assertTrue(myInfoPage.isPersonalDetailsHeaderDisplayed(), "Personal Details header is not displayed.");
    }

    @And("I update the personal details with the following values:")
    public void iUpdateThePersonalDetailsWithTheFollowingValues(io.cucumber.datatable.DataTable dataTable) {
        var userDetails = dataTable.asMaps(String.class, String.class).get(0);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("middleName")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@class, 'oxd-input') and @placeholder='yyyy-dd-mm']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Nationality']/following::div[1]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Marital Status']/following::div[1]")));

        // Wait for the overlay (if any) to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));

        String FirstName = userDetails.get("FirstName");
        String MiddleName = userDetails.get("MiddleName");
        String LastName = userDetails.get("LastName");
        String DateOfBirth = userDetails.get("DateOfBirth");
        String nationality = userDetails.get("Nationality");
        String maritalStatus = userDetails.get("MaritalStatus");
        String Gender = userDetails.get("Gender");
        String EmployeeId = userDetails.get("EmployeeId");
        String OtherId = userDetails.get("OtherId");
        String DriverLicenseNumber = userDetails.get("DriverLicenseNumber");
        String LicenseExpiryDate = userDetails.get("LicenseExpiryDate");


        myInfoPage.updateDetails(FirstName, MiddleName, LastName,DateOfBirth, nationality, maritalStatus, Gender,EmployeeId, OtherId,DriverLicenseNumber,LicenseExpiryDate);

    }

    @And("I save the personal details")
    public void iSaveThePersonalDetails() {

        // Call the method to click the save button
        myInfoPage.clickSaveButton();
    }


    @Then("the personal details should be updated successfully with the following values:")
    public void thePersonalDetailsShouldBeUpdatedSuccessfully(io.cucumber.datatable.DataTable dataTable) {
        var expectedDetails = dataTable.asMaps(String.class, String.class).get(0);
        // Retrieve the updated details from the input fields
        String updatedFirstName = myInfoPage.getFirstName();
        String updatedMiddleName = myInfoPage.getMiddleName();
        String updatedLastName = myInfoPage.getLastName();
        String updatedDateOfBirth = myInfoPage.getDateOfBirth();
        String updatedNationality = myInfoPage.getNationality();
        String updatedMaritalStatus = myInfoPage.getMaritalStatus();
        String updatedGender = myInfoPage.getGender();

        // Define the expected values
        String expectedFirstName = expectedDetails.get("FirstName");
        String expectedMiddleName = expectedDetails.get("MiddleName");
        String expectedLastName = expectedDetails.get("LastName");
        String expectedDateOfBirth = expectedDetails.get("DateOfBirth");
        String expectedNationality = expectedDetails.get("Nationality");
        String expectedMaritalStatus = expectedDetails.get("MaritalStatus");
        String expectedGender = expectedDetails.get("Gender");

        // Verify if the details match
        Assert.assertEquals(updatedFirstName, expectedFirstName, "First Name did not update correctly");
        Assert.assertEquals(updatedMiddleName, expectedMiddleName, "Middle Name did not update correctly");
        Assert.assertEquals(updatedLastName, expectedLastName, "Last Name did not update correctly");
        Assert.assertEquals(updatedDateOfBirth, expectedDateOfBirth, "Date of Birth did not update correctly");
        Assert.assertEquals(updatedNationality, expectedNationality, "Nationality mismatch.");
        Assert.assertEquals(updatedMaritalStatus, expectedMaritalStatus, "Marital Status mismatch.");
        Assert.assertEquals(updatedGender, expectedGender, "Gender mismatch.");
        Assert.assertEquals(myInfoPage.getEmployeeId(), expectedDetails.get("EmployeeId"));
        Assert.assertEquals(myInfoPage.getOtherId(), expectedDetails.get("OtherId"));
        Assert.assertEquals(myInfoPage.getDriverLicenseNumber(), expectedDetails.get("DriverLicenseNumber"));
        Assert.assertEquals(myInfoPage.getLicenseExpiryDate(), expectedDetails.get("LicenseExpiryDate"));

    }

}
