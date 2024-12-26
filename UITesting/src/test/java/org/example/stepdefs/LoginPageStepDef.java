// Login is done by 204031F Dassanayaka H.D.H.S.
package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.example.pages.LoginPage;

import java.time.Duration;

public class LoginPageStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Before
    public void setup(){
        // ****Set the path to your chromedriver executable here****
        System.setProperty("webdriver.chrome.driver", "C:/Users/hasit/Downloads/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }


    @Given("I am in the login page of the OrangeHRM Application")
    public void iAmInTheLoginPageOfTheOrangeHRMApplication() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
    }

    @When("I enter valid credentials")
    public void iEnterValidCredentials() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement loginButton =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        homePage=  loginPage.login("Admin", "admin123");
    }

    @Then("I should be taken to the Overview page")
    public void iShouldBeTakenToTheOverviewPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("OrangeHRM"));
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
        driver.quit();
    }
}
