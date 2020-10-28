package com.acme.cucumber;

import com.acme.studenthome.resource.PropertiesSystemResource.SavePropertyResource;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreatePropertySteps extends SpringIntegrationTest{
    SavePropertyResource resource = new SavePropertyResource();
    @Given("a landlord in the register property's view")
    public void aLandlordInTheRegisterPropertySView() {

    }

    @And("the information entered is correct")
    public void theInformationEnteredIsCorrect() {
        resource.setRooms(2L);
        resource.setSize(75.8F);
        resource.setCost(1500.50F);
        resource.setActive(false);
        resource.setAddress("Casa");
        resource.setPlace(1L);
    }

    @When("the landlord clicks the register button")
    public void theLandlordClicksTheRegisterButton() {
    }

    @And("make a post request to {string}")
    public void makeAPostRequestTo(String url) throws Throwable{
        //executeGet(url);
        executePost(url, resource);
    }

    @Then("the system promotes the ad of the property")
    public void theSystemPromotesTheAdOfTheProperty() {
    }

   /* @And("the result received has a status code of {int}")
    public void theResultReceivedHasAStatusCodeOf(int code) throws Throwable {
        //final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        //assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(code));
        assertThat(response.getStatusCode().value(), is(code));
    }*/

    @And("the information entered is incorrect")
    public void theInformationEnteredIsIncorrect() {
        resource = null;
    }

    @Then("the system asks to correct the wrong data")
    public void theSystemAsksToCorrectTheWrongData() {
    }

    @And("the result received has a OK status code of {int}")
    public void theResultReceivedHasAOKStatusCodeOf(int code) {
        assertThat(response.value(), is(code));
    }

    @And("the result received has a BAD status code of {int}")
    public void theResultReceivedHasABADStatusCodeOf(int code) {
        assertThat(response.value(), is(HttpStatus.BAD_REQUEST.value()));
    }
}
