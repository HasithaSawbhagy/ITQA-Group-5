package org.example.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIStepDef {

    private final Map<Integer, Map<String, String>> mockDatabase = new HashMap<>();
    private String lastOperation = "";
    private int lastStatusCode;

    @Given("I am an admin")
    public void i_am_an_admin() {
        // Simulate admin setup
        System.out.println("Admin setup initialized.");
    }

    @When("I send a POST request to create the book")
    public void i_send_a_post_request_to_create_the_book(DataTable dataTable) {
        Map<String, String> bookData = dataTable.asMap(String.class, String.class);
        int id = mockDatabase.size() + 1;
        // Create a new HashMap to avoid the UnsupportedOperationException
        Map<String, String> mutableBookData = new HashMap<>(bookData);
        mutableBookData.put("id", String.valueOf(id));
        mockDatabase.put(id, mutableBookData);
        lastOperation = "CREATE";
        lastStatusCode = 201;
        System.out.println("Book created: " + mutableBookData);
    }

    @When("I send a PUT request to update the book")
    public void i_send_a_put_request_to_update_the_book(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        // Assuming the first row is the header, start from the second row
        for (int i = 1; i < rows.size(); i++) {
            List<String> row = rows.get(i);
            int id = Integer.parseInt(row.get(0));
            Map<String, String> bookData = new HashMap<>();
            bookData.put("id", String.valueOf(id));
            bookData.put("title", row.get(1));
            bookData.put("author", row.get(2));

            if (mockDatabase.containsKey(id)) {
                mockDatabase.put(id, bookData);
                lastOperation = "UPDATE";
                lastStatusCode = 200;
                System.out.println("Book updated: " + bookData);
            } else {
                lastOperation = "UPDATE_FAIL";
                lastStatusCode = 404;
                System.out.println("Book not found for update: " + id);
            }
        }
    }

    @When("I send a DELETE request for book with id {int}")
    public void i_send_a_delete_request_for_book_with_id(int id) {
        if (mockDatabase.containsKey(id)) {
            mockDatabase.remove(id);
            lastOperation = "DELETE";
            lastStatusCode = 200;
            System.out.println("Book deleted: " + id);
        } else {
            lastOperation = "DELETE_FAIL";
            lastStatusCode = 404;
            System.out.println("Book not found for deletion: " + id);
        }
    }

    @When("I send a GET request for all books")
    public void i_send_a_get_request_for_all_books() {
        System.out.println("All books fetched: " + mockDatabase.values());
        lastOperation = "GET_ALL";
        lastStatusCode = 200;
    }

    @When("I send a GET request for book with id {int}")
    public void i_send_a_get_request_for_book_with_id(int id) {
        if (mockDatabase.containsKey(id)) {
            System.out.println("Book fetched: " + mockDatabase.get(id));
            lastOperation = "GET_ONE";
            lastStatusCode = 200;
        } else {
            System.out.println("Book not found: " + id);
            lastOperation = "GET_ONE_FAIL";
            lastStatusCode = 404;
        }
    }

    @Then("I validate the response status is {int}")
    public void i_validate_the_response_status_is(int statusCode) {
        if (lastStatusCode == statusCode) {
            System.out.println("Status code validated successfully: " + statusCode);
        } else {
            throw new AssertionError("Expected: " + statusCode + ", but got: " + lastStatusCode);
        }
    }
}