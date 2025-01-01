package org.example.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import util.ConfigLoader;

import java.util.HashMap;
import java.util.Map;


public class UpdateBookAPIStepDef {
    private RequestSpecification request;
    private Response response;

    @Given("I am authenticated as {string}")
    public void iAmAuthenticatedAs(String userRole) {
        String username = ConfigLoader.getProperty("username." + userRole);
        String password = ConfigLoader.getProperty("password");

        request = RestAssured.given()
                .baseUri(ConfigLoader.getProperty("baseUrl"))
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json");
    }

    @When("I send a PUT request to {string} with the following details:")
    public void iSendAPutRequestToWithTheFollowingDetails(String endpoint, DataTable dataTable) {
        Map<String, String> data = new HashMap<>(dataTable.asMaps().get(0));

        // Handle empty or null values in the feature file
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (entry.getValue() == null || entry.getValue().trim().isEmpty()) {
                data.put(entry.getKey(), ""); // Replace null/empty with an empty string
            }
        }

        // Send the PUT request with the modified data
        response = request.body(data).when().put(endpoint);
    }

    @Then("I should receive a status code of {int}")
    public void iShouldReceiveAStatusCodeOf(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode, "Unexpected status code!");
    }

    @Then("the response should contain:")
    public void theResponseShouldContain(DataTable expectedDataTable) {
        Map<String, String> expectedData = expectedDataTable.asMaps().get(0);
        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            Assert.assertEquals(response.jsonPath().getString(entry.getKey()), entry.getValue(),
                    "Mismatch in response field: " + entry.getKey());
        }
    }

    @Then("the response message should be {string}")
    public void theResponseMessageShouldBe(String expectedMessage) {
        String contentType = response.getHeader("Content-Type");
        if (contentType != null && contentType.contains("application/json")) {
            String actualMessage = response.jsonPath().getString("message");
            Assert.assertEquals(actualMessage, expectedMessage, "Mismatch in response message!");
        } else {
            String actualMessage = response.getBody().asString();
            Assert.assertEquals(actualMessage, expectedMessage, "Mismatch in response message!");
        }
    }
}

