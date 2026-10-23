Feature: Favorite Parks List
  Users should be able to manage the list of the parks they added to favorites through this page.
  Users should be able to scroll through these parks and remove any as they wish.
  Users should also be able to compare and reorder parks on their list.

# Empty background, not allowed
#  Background: User is logged in and on their Favorite Park List page

  Scenario: Add a park to favorites
    Given the user searches for a park and displays its details
    When the user clicks the option to add the park to their favorites
    Then the park should be added to their favorites list
    And the user should receive confirmation of the addition

  Scenario: Reorder parks in the favorites list
    Given the user is viewing their favorite park list
    When the user hovers over park, clicks up or down arrow to change order
    And confirms the reordering action
    Then the park should move to its new position within the list
    And the user should see the updated order of the favorite parks list

  Scenario: Cancel reordering of the favorite park
    Given the user attempts to reorder their favorite park list
    When the user clicks cancel on the reordering confirmation popup
    Then no changes should be made to the order of the favorite park list

  Scenario: Save reordering
    Given the user has reordered their favorite park list and navigated away
    When the user returns to their favorite park list page
    Then they should see their favorite parks in the new order they arranged

  Scenario: Delete all parks from the favorites list
    Given the user decides to clear their favorite park list
    When the user clicks the delete button and confirms the action
    Then the user's favorite park list should be empty

  Scenario: Cancel deletion of all parks from the favorites list
    Given the user initiates the deletion of all parks from their favorite list
    When the user clicks cancel on the deletion confirmation popup
    Then no parks should be removed from the user's favorite list

  Scenario: Change favorite parks list visibility to public
    Given the user's favorite park list is set to private
    When the user changes the list visibility to public
    Then the list should be visible to others
    And the change should persist when navigating away and returning
