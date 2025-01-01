package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import util.ConfigLoader;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;


public class GetAllBooksAPIStepDef {

    private Response response;

    @Before
    public void setUp() {
        // Reset authentication before each scenario
        RestAssured.authentication = null;
    }

    @After
    public void tearDown() {
        // Clear authentication after each scenario to ensure no carryover
        RestAssured.authentication = null;
    }


    @Given("I have valid credentials for {string} to get all books")
    public void iHaveValidCredentialsForGetAllBooks(String role) {
        // Set base URI
        RestAssured.baseURI = ConfigLoader.getProperty("baseUrl");

        // Clear previous authentication
        RestAssured.authentication = null;

        // Set authentication based on the role
        if ("user".equalsIgnoreCase(role)) {
            RestAssured.authentication = RestAssured.basic(
                    ConfigLoader.getProperty("username.user"),
                    ConfigLoader.getProperty("password.user")
            );
        } else if ("admin".equalsIgnoreCase(role)) {
            RestAssured.authentication = RestAssured.basic(
                    ConfigLoader.getProperty("username.admin"),
                    ConfigLoader.getProperty("password.admin")
            );
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    @Given("I have invalid username credentials for {string} to get all books")
    public void iHaveInvalidUsernameCredentialsForGetAllBooks(String role) {
        // Set base URI
        RestAssured.baseURI = ConfigLoader.getProperty("baseUrl");

        // Clear previous authentication
        RestAssured.authentication = null;

        if ("user".equalsIgnoreCase(role)) {
            RestAssured.authentication = RestAssured.basic(
                    "invalid_"+ConfigLoader.getProperty("username.user"),
                    ConfigLoader.getProperty("password.user")
            );
        } else if ("admin".equalsIgnoreCase(role)) {
            RestAssured.authentication = RestAssured.basic(
                    "invalid_"+ConfigLoader.getProperty("username.admin"),
                    ConfigLoader.getProperty("password.admin")
            );
        }

    }

    @Given("I have invalid password credentials for {string} to get all books")
    public void iHaveInvalidPasswordCredentialsForGetAllBooks(String role) {
        // Set base URI
        RestAssured.baseURI = ConfigLoader.getProperty("baseUrl");

        // Clear previous authentication
        RestAssured.authentication = null;

        if ("user".equalsIgnoreCase(role)) {
            RestAssured.authentication = RestAssured.basic(
                    ConfigLoader.getProperty("username.user"),
                    "invalid_"+ConfigLoader.getProperty("password.user")
            );
        } else if ("admin".equalsIgnoreCase(role)) {
            RestAssured.authentication = RestAssured.basic(
                    ConfigLoader.getProperty("username.admin"),
                    "invalid_"+ConfigLoader.getProperty("password.admin")
            );
        }
    }

    @Given("I have all invalid credentials to get all books")
    public void iHaveAllInvalidCredentialsForGetAllBooks() {
        // Set base URI
        RestAssured.baseURI = ConfigLoader.getProperty("baseUrl");

        // Clear previous authentication
        RestAssured.authentication = null;

        RestAssured.authentication = RestAssured.basic(
                "invalid_"+ConfigLoader.getProperty("username.user"),
                "invalid_"+ConfigLoader.getProperty("password.user")
        );

    }

    @When("I request to get all books")
    public void iRequestToGetAllBooks() {
        // Make the GET request
        response = RestAssured.given()
                .get("/api/books");

        // Log the response for debugging
        System.out.println("Get All Books Response Body: " + response.getBody().asString());
    }

    @Then("the get all books API response should return status code {int}")
    public void theAPIResponseShouldReturnStatusCode(int statusCode) {
        // Assert status code
        Assert.assertEquals(response.getStatusCode(), statusCode, "Status code mismatch!");
    }

    @Then("the response should contain a list of books")
    public void theResponseShouldContainAListOfBooks() {
        response.then().body("", not(empty()));
    }
}