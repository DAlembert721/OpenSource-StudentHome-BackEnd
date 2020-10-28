package com.acme.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreatePropertySteps extends SpringIntegrationTest{
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
    public void makeAPostRequestTo(String url) throws Throwable{
        executeGet(url);
    }

    @Then("the system promotes the ad of the property")
    public void theSystemPromotesTheAdOfTheProperty() {
    }

    @And("the result received has a status code of {int}")
    public void theResultReceivedHasAStatusCodeOf(int code) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(code));
    }

    @And("the information entered is incorrect")
    public void theInformationEnteredIsIncorrect() {
    }

    @Then("the system asks to correct the wrong data")
    public void theSystemAsksToCorrectTheWrongData() {
    }
}
