package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.AddVacancyPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

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

    @Then("the vacancy should be added successfully")
    public void theVacancyShouldBeAddedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isMessageVisible = wait.until(driver -> driver.getPageSource().contains("Successfully Saved"));

        Assert.assertTrue(isMessageVisible, "Vacancy was not added successfully!");
    }
}
