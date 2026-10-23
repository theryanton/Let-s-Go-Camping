# use case for first search (default) (no filter)

Feature: Searching the park list
  Scenario: First search (default) (no filter)
    Given I am on the park list page
    And I type in the name of a park in the search bar
    And I search up a park by clicking the search button
    Then I should see up to 10 results of what was returned by the API

  Scenario: First search click on park
    Given I am on the park list page
    And I type in the name of a park in the search bar
    And I search up a park by clicking the search button
    And I click on a park
    Then I should see the park details

# use case for second search

  Scenario: Second search
    Given I am on the park list page
    And I type in the name of a park in the search bar
    And I search up a park by clicking the search button
    And I click the button to "load 10 more results + display 10 more results"
    Then I should see up to 10 more results of what was returned by the API

# use case for searching with keyboard (enter + tab)

  Scenario: Searching with keyboard (enter + tab)
    Given I am on the park list page
    And I type in the name of a park in the search bar
    And I navigate to the search button via keyboard by pressing tab and enter
    Then I should see up to 10 results of what was returned by the API

# 4 use cases for filtering + 1 for default

  Scenario: Filtering by name
    Given I am on the park list page
    And I type in the name of a park in the search bar
    And I search up a park by clicking the search button
    And I use the filter to filter by Name
    Then I should see up to 10 name filtered results returned by the API

  Scenario: Filtering by amenity
    Given I am on the park list page
    And I type in the name of a park in the search bar
    And I search up a park by clicking the search button
    And I use the filter to filter by Amenity
    Then I should see up to 10 amenity filtered results returned by the API

  Scenario: Filtering by state
    Given I am on the park list page
    And I type in the name of a park in the search bar
    And I search up a park by clicking the search button
    And I use the filter to filter by State
    Then I should see up to 10 state filtered results returned by the API

  Scenario: Filtering by activity
    Given I am on the park list page
    And I type in the name of a park in the search bar
    And I search up a park by clicking the search button
    And I use the filter to filter by Activity
    Then I should see up to 10 activity filtered results returned by the API