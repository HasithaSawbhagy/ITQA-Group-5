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

public class PostBookNullValuesAPIStepDef {

    private Response response;
    private JSONObject requestBody;
    private int existingBookId;

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
    @Given("I have valid credentials for {string} to create book")
    public void iHaveValidCredentialsForCreateBook(String role) {
        setAuthentication(role);
    }

    @Given("there is an existing book with id {string}")
    public void thereIsAnExistingBookWithId(String id) {
        existingBookId = Integer.parseInt(id);

    }

    @When("I send POST request with valid book details")
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


    @When("I send POST request with null title")
    public void iSendAPOSTRequestWithNullTitle() {
        requestBody.put("title", JSONObject.NULL); // Use JSONObject.NULL for null values
        requestBody.put("author", "Test Author");

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/api/books");
        System.out.println("Post request with null title: " + response.getBody().asString());
    }

    @When("I send POST request with null author")
    public void iSendAPOSTRequestWithNullAuthor() {
        requestBody.put("title", "Test Book2");
        requestBody.put("author", JSONObject.NULL); // Use JSONObject.NULL for null values

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/api/books");
        System.out.println("Post request with null author: " + response.getBody().asString());
    }

    @When("I send a POST request with existing id {string}")
    public void iSendAPOSTRequestWithExistingId(String id) {
        requestBody.put("id", Integer.parseInt(id));
        requestBody.put("title", "Test Book");
        requestBody.put("author", "Test Author");

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/api/books");
        System.out.println("Post request with existing ID: " + response.getBody().asString());
    }


    @Then("The API response should return status code {int}")
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