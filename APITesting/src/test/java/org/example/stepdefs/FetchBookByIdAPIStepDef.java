package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import util.ConfigLoader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class FetchBookByIdAPIStepDef {

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


    @Given("I have valid credentials for {string}")
    public void iHaveValidCredentialsFor(String role) {
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

    @When("I fetch the book details by ID {string}")
    public void iFetchTheBookDetailsByID(String bookId) {

        // Make the GET request
        response = RestAssured.given()
                .pathParam("id", bookId)
                .get("/api/books/{id}");

        // Log the response for debugging
        System.out.println(response.getBody().asString());
    }

    @Then("the API response should return status code {int}")
    public void theAPIResponseShouldReturnStatusCode(int statusCode) {
        // Assert status code
        Assert.assertEquals(response.getStatusCode(), statusCode, "Status code mismatch!");
    }

    @And("the response should contain the book details for ID {string}")
    public void theResponseShouldContainTheBookDetailsForID(String bookId) {
        // Validate response details
        Assert.assertNotNull(response.jsonPath().getString("id"), "Book ID is missing in the response!");
        Assert.assertEquals(response.jsonPath().getString("id"), bookId, "Book ID mismatch in the response!");
    }

    @And("the response should contain the message {string}")
    public void theResponseShouldContainTheMessage(String expectedMessage) {
        // Validate response details for non-existing book
        String actualMessage = response.getBody().asString();
        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Response message mismatch!");
    }
}
