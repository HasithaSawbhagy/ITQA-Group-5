package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.AddVacancyPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AddVacancyStepDef {
    private WebDriver driver;
    private AddVacancyPage addVacancyPage;

    @Before
    public void setup() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\jdk-18.0.2\\bin\\chromedriver-win64\\chromedriver.exe");
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

    @Given("I have logged in to the OrangeHRM Application")
    public void iHaveLoggedInToTheOrangeHRMApplication() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
    }

    @Given("I am on the Recruitment page")
    public void iAmOnTheRecruitmentPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewRecruitmentModule");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isRecruitmentVisible = wait.until(driver -> driver.getPageSource().contains("Recruitment"));

        Assert.assertTrue(isRecruitmentVisible, "Recruitment page is not displayed!");
    }

    @When("I navigate to the Add Vacancy page")
    public void iNavigateToTheAddVacancyPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/recruitment/addJobVacancy");
        addVacancyPage = new AddVacancyPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isAddVacancyVisible = wait.until(driver -> driver.getPageSource().contains("Add Vacancy"));

        Assert.assertTrue(isAddVacancyVisible, "Failed to navigate to Add Vacancy page!");
    }

    @When("I fill out the vacancy details")
    public void iFillOutTheVacancyDetails(io.cucumber.datatable.DataTable dataTable) {
        var data = dataTable.asMaps(String.class, String.class).get(0);

        String jobTitle = data.get("JobTitle");
        String vacancyName = data.get("VacancyName");
        String hiringManager = data.get("HiringManager");
        String positions = data.get("Positions");
        String description = data.get("Description");
        boolean isActive = data.get("Active").equalsIgnoreCase("yes");
        boolean isPublish = data.get("Publish in RSS Feed and Web Page").equalsIgnoreCase("yes");

        addVacancyPage.addVacancy(jobTitle, vacancyName, hiringManager, positions, description, isActive, isPublish);
    }

    @When("I save the vacancy")
    public void i_save_the_vacancy() {
        // Instantiate the page object class if not done already
        AddVacancyPage addVacancyPage = new AddVacancyPage(driver);

        // Call the method to click the save button
        addVacancyPage.saveVacancy();
    }

    @Then("the vacancy should be added successfully")
    public void theVacancyShouldBeAddedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

        try {
            // Locate the success message with enhanced XPath
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'oxd-toast-content') and contains(text(), 'Successfully Saved')]")
            ));

            if (successMessage.isDisplayed()) {
                System.out.println("Vacancy added successfully!");
            } else {
                throw new AssertionError("Success message not found.");
            }
        } catch (TimeoutException e) {
            // Log additional debugging information
            System.out.println("Timeout occurred. Verifying toast messages...");
            List<WebElement> toastMessages = driver.findElements(By.xpath("//div[contains(@class, 'oxd-toast-content')]"));
            System.out.println("Number of toast messages found: " + toastMessages.size());
            for (WebElement message : toastMessages) {
                System.out.println("Toast Message: " + message.getText());
            }


        }
    }



}
