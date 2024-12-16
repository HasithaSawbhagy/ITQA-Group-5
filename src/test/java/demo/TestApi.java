package demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestApi {

    @Test
    public void getAllBooks() {
        Response response = RestAssured.given()
                .auth()
                .basic("admin", "password") // or other auth method
                .when()
                .get("http://localhost:7081/api/books/2");

        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
