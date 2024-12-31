package org.example.stepdefs;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class FetchBookByIdAPIStepDef {
    private static final Properties prop = new Properties();
    private Response response;

    @Given("I have valid credentials for {string}")
    public void iHaveValidCredentialsFor(String role) {
        try {
            FileInputStream fs = new FileInputStream("src/main/resources/config.properties");
            prop.load(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RestAssured.baseURI = prop.getProperty("baseUrl");
        if (role.equals("user")) {
            RestAssured.authentication = RestAssured.basic(prop.getProperty("username.user"), prop.getProperty("password"));
        } else if (role.equals("admin")) {
            RestAssured.authentication = RestAssured.basic(prop.getProperty("username.admin"), prop.getProperty("password"));
        }
    }

    @When("I fetch the book details by ID {string}")
    public void iFetchTheBookDetailsByID(String bookId) {
        response = RestAssured.given()
                .pathParam("id", bookId)
                .get("/api/books/{id}");
    }

    @Then("the API response should return status code {int}")
    public void theAPIResponseShouldReturnStatusCode(int statusCode) {
        assertEquals(response.getStatusCode(), statusCode, "Status code mismatch!");
    }

    @And("the response should contain the book details for ID {string}")
    public void theResponseShouldContainTheBookDetailsForID(String bookId) {
        assertNotNull(response.jsonPath().getString("id"), "Book ID is missing in the response!");
        assertEquals(response.jsonPath().getString("id"), bookId, "Book ID mismatch in the response!");
    }
}
