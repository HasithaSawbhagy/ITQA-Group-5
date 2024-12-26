package com.example.UITesting;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import java.time.Duration;


public class LoginSteps {

    @Autowired
    private WebDriver driver;


    @Given("I am in the login page of the OrangeHRM Application")
    public void iAmInTheLoginPageOfTheOrangeHRMApplication() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }


    @When("I enter valid credentials")
    public void iEnterValidCredentials() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));

        usernameField.sendKeys("Admin");
        passwordField.sendKeys("admin123");
        loginButton.click();
    }


    @Then("I should be taken to the Overview page")
    public void iShouldBeTakenToTheOverviewPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("OrangeHRM"));
        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        if (!actualTitle.equals(expectedTitle)) {
            Assertions.fail("Login failed. Expected title is " + expectedTitle + ", but the actual title was " + actualTitle);
        }
        Assertions.assertEquals(expectedTitle,actualTitle);
        driver.quit();
    }
}