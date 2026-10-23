package edu.usc.csci310.project;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CompareFavoriteStepdefs {
        // ADD FUNCTIONALITY
        @Given("the user selects a friend with a public favorite park list to compare")
        public void the_user_selects_a_friend_with_a_public_favorite_park_list_to_compare() {

        }
        @When("the user initiates the comparison")
        public void the_user_initiates_the_comparison() {

        }
        @Then("the combined list of unique parks from both users should be displayed")
        public void the_combined_list_of_unique_parks_from_both_users_should_be_displayed() {

        }
        @Then("the parks should be ranked in descending order by favorites ratio")
        public void the_parks_should_be_ranked_in_descending_order_by_favorites_ratio() {

        }
        @Given("the user selects a friend with a private favorite park list to compare")
        public void the_user_selects_a_friend_with_a_private_favorite_park_list_to_compare() {

        }
        @When("the user attempts to initiate the comparison")
        public void the_user_attempts_to_initiate_the_comparison() {

        }
        @Then("an error message should be displayed indicating the list is private and cannot be compared")
        public void an_error_message_should_be_displayed_indicating_the_list_is_private_and_cannot_be_compared() {

        }
        @Given("the user attempts to compare their favorite park list with a user that does not exist")
        public void the_user_attempts_to_compare_their_favorite_park_list_with_a_user_that_does_not_exist() {

        }
        @Then("an error message should be displayed indicating the user does not exist")
        public void an_error_message_should_be_displayed_indicating_the_user_does_not_exist() {

        }
        @Given("I have generated a list of national parks for person A and B")
        public void i_have_generated_a_list_of_national_parks_for_person_a_and_b() {

        }
        @Given("a specific park {string} is on the list")
        public void a_specific_park_is_on_the_list(String string) {
        }
        @When("I check if {string} is on person A's list")
        public void i_check_if_is_on_person_a_s_list(String string) {
        }
        @Then("store the name or ID of the park with the user")
        public void store_the_name_or_id_of_the_park_with_the_user() {

        }
        @Then("compare in the database for union")
        public void compare_in_the_database_for_union() {

        }

        @When("I check if {string} is ranked higher than {string}")
        public void i_check_if_is_ranked_higher_than(String string, String string2) {
        }
        @Then("{string} should be ranked higher")
        public void should_be_ranked_higher(String string) {
        }
        @When("I check the ratio displayed for {string}")
        public void i_check_the_ratio_displayed_for(String string) {
        }
        @Then("the ratio should be displayed as {string}")
        public void the_ratio_should_be_displayed_as(String string) {
        }
        @When("I hover over the ratio")
        public void i_hover_over_the_ratio() {

        }
        @Then("I should see the usernames of the people who favorited the park")
        public void i_should_see_the_usernames_of_the_people_who_favorited_the_park() {

        }
        @When("I click on the park {string}")
        public void i_click_on_the_park(String string) {
        }
        @Then("the park detail window should be displayed")
        public void the_park_detail_window_should_be_displayed() {

        }

        @Given("I am on the Compare Favorites page CompareFav")
        public void iAmOnCompareFavorites_CompareFav() {

        }

        @When("I click on the link to the login page in the navbar CompareFav")
        public void iClickOnLogin_CompareFav() {

        }

        @Then("I should be redirected to the login page CompareFav")
        public void iRedirectedToLogin_CompareFav() {

        }

        @When("I click on the link to the registration page in the navbar CompareFav")
        public void iClickOnRegistration_CompareFav() {

        }

        @Then("I should be redirected to the registration page CompareFav")
        public void iRedirectedToRegistration_CompareFav() {

        }

        @When("I click on the link to the search page in the navbar CompareFav")
        public void iClickOnSearch_CompareFav() {

        }

        @Then("I should be redirected to the search page CompareFav")
        public void iRedirectedToSearch_CompareFav() {

        }

        @And("I am logged in CompareFav")
        public void iAmLoggedIn_CompareFav() {

        }

        @Then("I should be redirected to the favorites page CompareFav")
        public void iRedirectedToFavorites_CompareFav() {
        }

        @When("I click on the link to the favorites page in the navbar CompareFav")
        public void iClickOnFavorites_CompareFav() {
        }

        @And("park {string} on list with {int} favorites. {string} is on list with {int} favorite")
        public void parkOnListWithFavoritesIsOnListWithFavorite(String arg0, int arg1, String arg2, int arg3) {
        }
}