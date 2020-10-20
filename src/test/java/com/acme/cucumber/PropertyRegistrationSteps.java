package com.acme.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

public class PropertyRegistrationSteps {
    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl = "http://localhost";
    @Given("a landlord in the register property's view")
    public void aLandlordInTheRegisterPropertySView() {
    }

    @And("the information entered is correct")
    public void theInformationEnteredIsCorrect() {
    }

    @When("the landlord clicks the register button")
    public void theLandlordClicksTheRegisterButton() {
    }

    @And("make a post request to {string}")
    public void makeAPostRequestTo(String arg0) {
    }

    @Then("the system promotes the ad of the property")
    public void theSystemPromotesTheAdOfTheProperty() {
    }

    @And("the result received has a status code of {int}")
    public void theResultReceivedHasAStatusCodeOf(int arg0) {
    }

    @And("the information entered is incorrect")
    public void theInformationEnteredIsIncorrect() {
    }

    @Then("the system asks to correct the wrong data")
    public void theSystemAsksToCorrectTheWrongData() {
    }
}
