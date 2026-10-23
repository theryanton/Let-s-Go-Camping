package edu.usc.csci310.project;

import edu.usc.csci310.project.models.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParkListSearchStepDefinitions {
    //so that port can be modified if needed
    @LocalServerPort
    private int port = 8080;

    @Given("I am on the park list page")
    public void iAmOnTheParkListPage() {
    }

    @And("I type in the name of a park in the search bar")
    public void iTypeInTheNameOfAParkInTheSearchBar() {

    }

    @And("I search up a park by clicking the search button")
    public void iSearchUpAParkByClickingTheSearchButton() {

    }

    @Then("I should see up to {int} results of what was returned by the API")
    public void iShouldSeeUpToResultsOfWhatWasReturnedByTheAPI(int arg0) {


    }

    @Then("I should see up to {int} more results of what was returned by the API")
    public void iShouldSeeUpToMoreResultsOfWhatWasReturnedByTheAPI(int arg0) {


    }

    @And("I click the button to {string}")
    public void iClickTheButtonTo(String arg0) {
    }

    @And("I navigate to the search button via keyboard by pressing tab and enter")
    public void iNavigateToTheSearchButtonViaKeyboardByPressingTabAndEnter() {
    }

    @And("I use the filter to filter by Name")
    public void iUseTheFilterToFilterByName() {

    }

    @And("I use the filter to filter by Amenity")
    public void iUseTheFilterToFilterByAmenity() {

    }

    @And("I use the filter to filter by State")
    public void iUseTheFilterToFilterByState() {

    }

    @And("I use the filter to filter by Activity")
    public void iUseTheFilterToFilterByActivity() {

    }

    @Then("I should see up to {int} name filtered results returned by the API")
    public void iShouldSeeUpToNameFilteredResultsReturnedByTheAPI(int arg0) {

    }

    @Then("I should see up to {int} amenity filtered results returned by the API")
    public void iShouldSeeUpToAmenityFilteredResultsReturnedByTheAPI(int arg0) {

    }

    @Then("I should see up to {int} state filtered results returned by the API")
    public void iShouldSeeUpToStateFilteredResultsReturnedByTheAPI(int arg0) {

    }

    @Then("I should see up to {int} activity filtered results returned by the API")
    public void iShouldSeeUpToActivityFilteredResultsReturnedByTheAPI(int arg0) {
    }

    @And("I click on a park")
    public void iClickOnAPark() {
        
    }

    @Then("I should see the park details")
    public void iShouldSeeTheParkDetails() {
    }
}