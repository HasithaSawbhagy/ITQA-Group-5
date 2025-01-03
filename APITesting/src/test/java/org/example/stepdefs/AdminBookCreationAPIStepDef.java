package org.example.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import util.ConfigLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminBookCreationAPIStepDef {

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

    @Given("I am logged in as an {string}")
    public void iAmLoggedInAsAn(String userRole) {
        // Fetch credentials for the given user role
        String username = ConfigLoader.getProperty("username." + userRole);
        String password = ConfigLoader.getProperty("password." + userRole);

        // Setup RestAssured base URI and authentication
        RestAssured.baseURI = ConfigLoader.getProperty("baseUrl");
        RestAssured.authentication = RestAssured.basic(username, password);
    }

    @When("I create new books by sending POST requests to {string} with the following details:")
    public void iCreateNewBooksBySendingPOSTRequestsToWithTheFollowingDetails(String endpoint, DataTable dataTable) {
        List<Map<String, String>> bookDetailsList = dataTable.asMaps();
        for (Map<String, String> bookDetails : bookDetailsList) {
            Response currentResponse = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(bookDetails)
                    .when()
                    .post(endpoint);

            // Save the last response for validation (extend as needed to save all responses)
            response = currentResponse;
        }
    }

    @When("I create a new book by sending a POST request to {string} with the following details:")
    public void iCreateANewBookBySendingAPOSTRequestToWithTheFollowingDetails(String endpoint, DataTable dataTable) {
        // Convert DataTable to Map
        Map<String, String> bookDetails = new HashMap<>(dataTable.asMaps().get(0));

        // Send POST request with book details
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bookDetails)
                .when()
                .post(endpoint);
    }

    @Then("the API should return a status code of {int} for all requests")
    public void theAPIShouldReturnAStatusCodeOfForAllRequests(int expectedStatusCode) {
        // Validate the status code for the last response
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected status code!");
    }

    @And("the responses should confirm the books were created with:")
    public void theResponsesShouldConfirmTheBooksWereCreatedWith(DataTable expectedDataTable) {
        // Assert that the response body is not empty
        Assert.assertFalse(response.getBody().asString().isEmpty(), "Response body is empty!");
    }

    @Then("the API should return a status code of {int}")
    public void theAPIShouldReturnAStatusCodeOf(int expectedStatusCode) {
        // Assert status code
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected status code!");
    }

    @And("the response should have an error message {string}")
    public void theResponseShouldHaveAnErrorMessage(String expectedErrorMessage) {
        String responseBody = response.getBody().asString();

        // If response is not JSON, compare directly with plain text
        Assert.assertEquals(responseBody.trim(), expectedErrorMessage, "Unexpected error message in the response!");
    }
}
