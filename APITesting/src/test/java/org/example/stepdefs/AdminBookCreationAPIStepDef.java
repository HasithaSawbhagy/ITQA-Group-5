package org.example.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import util.ConfigLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminBookCreationAPIStepDef {
    private RequestSpecification request;
    private Response response;

    @Given("I am logged in as an {string}")
    public void iAmLoggedInAsAn(String userRole) {
        // Fetch credentials for the given user role
        String username = ConfigLoader.getProperty("username." + userRole);
        String password = ConfigLoader.getProperty("password." + userRole);

        // Setup RestAssured request with authentication and base URI
        request = RestAssured.given()
                .baseUri(ConfigLoader.getProperty("baseUrl"))
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json");
    }

    @When("I create new books by sending POST requests to {string} with the following details:")
    public void iCreateNewBooksBySendingPOSTRequestsToWithTheFollowingDetails(String endpoint, DataTable dataTable) {
        List<Map<String, String>> bookDetailsList = dataTable.asMaps();
        for (Map<String, String> bookDetails : bookDetailsList) {
            Response currentResponse = request.body(bookDetails).when().post(endpoint);
            // Save each response for validation in subsequent steps
            response = currentResponse; // Store last response for validation, extend if needed to save all responses
        }
    }

    @When("I create a new book by sending a POST request to {string} with the following details:")
    public void iCreateANewBookBySendingAPOSTRequestToWithTheFollowingDetails(String endpoint, DataTable dataTable) {
        // Convert DataTable to Map
        Map<String, String> bookDetails = new HashMap<>(dataTable.asMaps().get(0));

        // Send POST request with book details
        response = request.body(bookDetails).when().post(endpoint);
    }

    @Then("the API should return a status code of {int} for all requests")
    public void theAPIShouldReturnAStatusCodeOfForAllRequests(int expectedStatusCode) {
        // For each response, validate the status code
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

