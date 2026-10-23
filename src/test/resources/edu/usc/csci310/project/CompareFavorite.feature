Feature: Comparing Favorite Parks with Friends
  Users should be able to compare their favorites list with others.
  Users can compare their list with others' public lists.
  Users should not be able to compare their list with users who don't exist.

  # Background is used to set up the state of the system before each scenario, not necessary atm
#  Background: User is logged in and on the Compare Favorite Parks page

  Scenario: After a comparison, receive a union list. Verify content is correct
    Given I have generated a list of national parks for person A and B
    And a specific park "NP" is on the list
    When I check if "NP" is on person A's list
    Then store the name or ID of the park with the user
    And compare in the database for union

  Scenario: Check we show the parks list in order by the correct ranking
    Given I have generated a list of national parks for person A and B
    And park "NP" on list with 2 favorites. "NM" is on list with 1 favorite
    When I check if "NP" is ranked higher than "NM"
    Then "NP" should be ranked higher

  Scenario: Checking that we are displaying the ratio on the page
    Given I have generated a list of national parks for person A and B
    And park "NP" on list with 2 favorites. "NM" is on list with 1 favorite
    When I check the ratio displayed for "NP"
    Then the ratio should be displayed as "2/2"

  Scenario: Hovering over ratio will show usernames
    Given I have generated a list of national parks for person A and B
    And park "NP" on list with 2 favorites. "NM" is on list with 1 favorite
    When I check the ratio displayed for "NP"
    And I hover over the ratio
    Then I should see the usernames of the people who favorited the park

  Scenario: when we click on a park, a window will pop up with more park details
    Given I have generated a list of national parks for person A and B
    And park "NP" on list with 2 favorites. "NM" is on list with 1 favorite
    When I click on the park "NP"
    Then the park detail window should be displayed

  Scenario:  Check if navigation bar can navigate to login/home page
    Given I am on the Compare Favorites page CompareFav
    When I click on the link to the login page in the navbar CompareFav
    Then I should be redirected to the login page CompareFav

  Scenario:  Check if navigation bar can navigate to registration page
    Given I am on the Compare Favorites page CompareFav
    When I click on the link to the registration page in the navbar CompareFav
    Then I should be redirected to the registration page CompareFav

  Scenario:  Check if navigation bar can navigate to search page
    Given I am on the Compare Favorites page CompareFav
    When I click on the link to the search page in the navbar CompareFav
    Then I should be redirected to the search page CompareFav

  Scenario:  Check if navigation bar can navigate to favorites page
    Given I am on the Compare Favorites page CompareFav
    And I am logged in CompareFav
    When I click on the link to the favorites page in the navbar CompareFav
    Then I should be redirected to the favorites page CompareFav
