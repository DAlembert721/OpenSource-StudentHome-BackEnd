package com.acme.cucumber;

import com.acme.studenthome.resource.PropertiesSystemResource.SavePropertyCommentResource;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CommentAPropertySteps extends SpringIntegrationTest{
    SavePropertyCommentResource commentResource = new SavePropertyCommentResource();
    @Given("a student in the property view")
    public void aStudentInThePropertyView() {
    }

    @And("the property is available")
    public void thePropertyIsAvailable() {
        commentResource.setComment("It's a comment");
        commentResource.setScore(5L);
    }

    @When("the student clicks the accept button")
    public void theStudentClicksTheAcceptButton() {
    }

    @And("make a post request to the url: {string}")
    public void makeAPostRequestToTheUrlPropertiesComments(String url) throws Throwable{
        executePost(url, commentResource);
    }

    @Then("the response has a code of {int}")
    public void theResponseHasACodeOf(int code) {
        assertThat(response.value(), is(code));
    }

    @And("the property is unavailable")
    public void thePropertyIsUnavailable() {
        commentResource = null;
    }
}
