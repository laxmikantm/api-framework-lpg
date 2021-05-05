package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.*;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import middleware.Controller;
import org.junit.Assert;
import requests.GET_Request;

import java.util.List;
import java.util.stream.IntStream;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**************************
 *  (C) L Somni            *
 ***************************/

public class StepDefs {
    TestContext testContext;
    Response response;
    Controller controller;

    GET_Request get_request = new GET_Request();

    public StepDefs(TestContext context) {
        testContext = context;
        response = testContext.getResponse();
        controller = testContext.getController();
    }

    @Given("I use {string} API end point")
    public void iUseAPIEndPoint(final String sEndPoint) {
        get_request.chooseEndPoint(sEndPoint);
        testContext.setMock(false);
    }

    @io.cucumber.java.en.And("I search for all attractions for {string}")
    public void iSearchForAllAttractionsFor(final String city) {
        get_request.chooseCityQueryString(city);
    }

    @io.cucumber.java.en.And("with attraction type as {string}")
    public void withAttractionTypeAs(final String type) {
        get_request.chooseAttractionTypeQueryString(type);
    }

    @When("I make a GET request")
    public void iMakeAGETRequest() {
        response = get_request.getRequest();
    }

    @Then("I get success response {int} code")
    public void iGetSuccessResponseCode(final int expectedStatusCode) {
        Assert.assertThat(response.statusCode(), is(equalTo(expectedStatusCode)));
    }

    @And("response contains correct structure for the cities end point response")
    public void responseContainsStructure() {
        response.then().body(matchesJsonSchemaInClasspath("cities_schema.json"));
    }

    @And("response has the cities")
    public void itContainsCities(List<List<String>> cities) {
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();

        IntStream.range(0, cities.get(0).size()).forEach(i -> {
            Assert.assertEquals(true, bodyAsString.toLowerCase().contains(cities.get(0).get(i).toLowerCase()));
        });
    }



}
