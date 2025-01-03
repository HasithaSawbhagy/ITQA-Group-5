package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.FormPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class FormStepDef {
    private WebDriver driver;
    private FormPage formPage;
    private LoginPage loginPage;

    @Before
    public void setup(){
        // Setting up thd Chrome driver to execute from here
        System.setProperty("web driver.chrome.driver","C:\\Users\\DOWNLOAD\\Documents\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }


    @After
    public void tearDown(){
////         The browser will close when the tests have finished running
//        if(driver!=null){
//            driver.quit();
//        }
    }

    @Given("I am on the claim request form page")
    public void iAmOnTheFormPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // navigate to login page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement loginButton =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginPage = new LoginPage(driver); // initialize login page object
        loginPage.login("Admin", "admin123"); // perform login operation





        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/claim/submitClaim"); // then navigate to the claim submission page
        formPage = new FormPage(driver); //initialize the form page object
    }

    @When("I select {string} in the Event dropdown")
    public void iSelectOptionFromEventDropdown(String option) {
        formPage.selectEvent(option);
    }

    @When("I select {string} in the Currency dropdown")
    public void iSelectOptionFromCurrencyDropdown(String option) {
        formPage.selectCurrency(option);
    }

    @When("I enter {string} in the remarks")
    public void iEnterDataInTheRemarks(String data) {
        formPage.enterRemarks(data);
    }


    @And("I fill in the required fields with event {string}, currency {string}, remarks {string}")
    public void iFillInTheRequiredFields(String event, String currency, String remarks) {
        // Fill in the form fields
        formPage.selectEvent(event);
        formPage.selectCurrency(currency);
        formPage.enterRemarks(remarks);
    }


    @And("I click the create button")
    public void iClickTheCreateButtonFromStepDef() {
        formPage.clickCreateButton();
    }

    @And("I click the cancel button")
    public void iClickTheCancelButtonFromStepDef() {
        formPage.clickCancelButton();
    }

    @Then("I should see a success message")
    public void iShouldBeInTheSubmitPage() {

        // Expected URL
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/claim/submitClaim";

        // Wait for the URL to change to the expected value
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isUrlCorrect = wait.until(ExpectedConditions.urlToBe(expectedUrl));

        // Close the browser
        driver.quit();

    }


    @Then("I should be on the claim page")
    public void i_should_be_on_the_claim_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/claim/cancelClaim"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/claim/cancelClaim"));
   }
}


