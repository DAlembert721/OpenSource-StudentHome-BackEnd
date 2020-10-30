package com.acme.cucumber;

import com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource.SaveStudentOpinionResource;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateStudentOpinion extends SpringIntegrationTest{
    SaveStudentOpinionResource opinionResource = new SaveStudentOpinionResource();
    @Given("a landlord in the student profile view")
    public void aLandlordInTheStudentProfileView() {
    }

    @And("the student profile is available")
    public void theStudentProfileIsAvailable() {
        opinionResource.setContent("It's a opinion");
        opinionResource.setScore(5L);
    }

    @When("the landlord clicks the accept button")
    public void theLandlordClicksTheAcceptButton() {
    }

    @And("make a post request to the direction: {string}")
    public void makeAPostRequestToTheDirection(String url) throws Throwable{
        executePost(url, opinionResource);
    }

    @Then("system response the request with the code {int}")
    public void systemResponseTheRequestWithTheCode(int code) {
        assertThat(response.value(), is(code));
    }

    @And("he student profile is unavailable")
    public void heStudentProfileIsUnavailable() {
        opinionResource = null;
    }
}
