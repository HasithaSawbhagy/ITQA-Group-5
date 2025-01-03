package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import util.ConfigLoader;


public class PostBookAPIEmptyValueStepDef {

    private Response response;
    private JSONObject requestBody;

    @Before
    public void setUp() {
        // Reset authentication before each scenario
        RestAssured.authentication = null;
        requestBody = new JSONObject(); // Initialize requestBody
    }

    @After
    public void tearDown() {
        // Clear authentication after each scenario to ensure no carryover
        RestAssured.authentication = null;
    }

    // POST request step definitions
    @Given("I have valid credentials for {string} to create a book")
    public void iHaveValidCredentialsForCreateBook(String role) {
        setAuthentication(role);
    }

    @When("I send a POST request with valid book details")
    public void iSendAPOSTRequestWithValidBookDetails() {
        // Sample data for request
        requestBody.put("title", "Test Book");
        requestBody.put("author", "Test Author");

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/api/books");
        System.out.println("Post request with valid data :" +response.getBody().asString());
    }


    @When("I send a POST request with empty title")
    public void iSendAPOSTRequestWithEmptyTitle() {
        requestBody.put("title", "");
        requestBody.put("author", "Test Author");

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/api/books");
        System.out.println("Post request with empty title: " +response.getBody().asString());
    }


    @When("I send a POST request with empty author")
    public void iSendAPOSTRequestWithEmptyAuthor() {
        requestBody.put("title", "Test Book3");
        requestBody.put("author", "");

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/api/books");
        System.out.println("Post request with empty author: " +response.getBody().asString());
    }

    @Then("the post book API response should return status code {int}")
    public void thePostBookAPIResponseShouldReturnStatusCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode, "Status code mismatch!");
    }

    private void setAuthentication(String role) {
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
}