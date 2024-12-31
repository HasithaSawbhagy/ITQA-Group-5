package org.example.stepdefs;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.ConfigLoader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class FetchBookByIdAPIStepDef {

    private Response response;

    @Given("I have valid credentials for {string}")
    public void iHaveValidCredentialsFor(String role) {
        // Set base URI
        RestAssured.baseURI = ConfigLoader.getProperty("baseUrl");

        // Set authentication based on the role
        if ("user".equalsIgnoreCase(role)) {
            RestAssured.authentication = RestAssured.basic(
                    ConfigLoader.getProperty("username.user"),
                    ConfigLoader.getProperty("password")
            );
        } else if ("admin".equalsIgnoreCase(role)) {
            RestAssured.authentication = RestAssured.basic(
                    ConfigLoader.getProperty("username.admin"),
                    ConfigLoader.getProperty("password")
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
    }

    @Then("the API response should return status code {int}")
    public void theAPIResponseShouldReturnStatusCode(int statusCode) {
        // Assert status code
        assertEquals(response.getStatusCode(), statusCode, "Status code mismatch!");
    }

    @And("the response should contain the book details for ID {string}")
    public void theResponseShouldContainTheBookDetailsForID(String bookId) {
        // Validate response details
        assertNotNull(response.jsonPath().getString("id"), "Book ID is missing in the response!");
        assertEquals(response.jsonPath().getString("id"), bookId, "Book ID mismatch in the response!");
    }
}
