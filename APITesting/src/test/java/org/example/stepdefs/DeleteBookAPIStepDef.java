package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import util.ConfigLoader;

public class DeleteBookAPIStepDef {

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

    @Given("I have valid credentials for {string} to delete book")
    public void iHaveValidCredentialsForDelete(String role) {
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

    @When("I delete the book with ID {string}")
    public void iDeleteTheBookByID(String bookId) {
        // Make the DELETE request
        response = RestAssured.given()
                .pathParam("id", bookId)
                .delete("/api/books/{id}");

        // Log the response for debugging
        System.out.println("Delete Response Body: " + response.getBody().asString());
    }

    @Then("the delete API response should return status code {int}")
    public void theAPIResponseShouldReturnStatusCode(int statusCode) {
        // Assert status code
        Assert.assertEquals(response.getStatusCode(), statusCode, "Status code mismatch!");
    }
    @And("the delete response should contain the message {string}")
    public void theDeleteResponseShouldContainTheMessage(String expectedMessage) {
        // Validate response details for delete
        String actualMessage = response.getBody().asString();
        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Response message mismatch!");
    }


}