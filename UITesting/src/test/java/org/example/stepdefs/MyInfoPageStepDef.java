package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
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

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\jdk-18.0.2\\bin\\chromedriver.exe");
        driver = new ChromeDriver();
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
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement loginButton =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginPage.login("Admin", "admin123");
    }

    @When("I navigate to the My Info section")
    public void iNavigateToTheMyInfoSection() {
        myInfoPage = new MyInfoPage(driver);
        myInfoPage.navigateToMyInfo();
    }

    @Then("I should see my personal information")
    public void iShouldSeeMyPersonalInformation() {
        Assert.assertTrue(myInfoPage.isPersonalDetailsHeaderDisplayed(), "Personal Details header is not displayed.");
    }
}
