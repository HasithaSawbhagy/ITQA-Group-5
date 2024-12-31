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

   
}
