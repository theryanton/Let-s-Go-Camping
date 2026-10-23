Feature: Registration
  User registration test scenarios.
  Users should be able to register a new account that must have a unique username.
  After successful registration, the user should be redirected to the login page.

  Scenario: Username already taken
    Given a user is on the registration page
    And the user enters the username "Alpha"
    And the user enters the password "Ab9"
    And the user confirms the password "Ab9"
    When the user clicks the register button
    Then error "That username has already been taken." is returned

  Scenario: Successful Registration
    Given a user is on the registration page
    And the user enters the username "ABC"
    And the user enters the password "Ab9"
    And the user confirms the password "Ab9"
    When the user clicks the register button
    Then the user should be redirected to the login page

  Scenario: Invalid password
    Given a user is on the registration page
    And the user enters the username "ABC"
    And the user enters the password "AB9"
    And the user confirms the password "AB9"
    When the user clicks the register button
    Then error "Password needs 1 uppercase, 1 lowercase, 1 number." is returned

  Scenario: Passwords don't match
    Given a user is on the registration page
    And the user enters the username "ABC"
    And the user enters the password "AB9"
    And the user confirms the password "BC10"
    When the user clicks the register button
    Then error "Passwords do not match." is returned

  Scenario: Attempt cancellation
    Given a user is on the registration page
    When the user clicks the cancel button
    Then the confirm cancellation query should be displayed

  Scenario: User attempts to cancel registration, but clicks 'Never Mind!'
    Given a user is on the registration page
    And the user enters the username "Test"
    And the user enters the password "Test"
    And the user confirms the password "Test"
    And the user clicks the cancel button
    When the user clicks Never Mind!
    Then the confirm cancellation query should be hidden
    And the form keeps username "Test", password "Test", and confirm "Test"

  Scenario: Cancel and confirm cancel
    Given a user is on the registration page
    And the user enters the username "Test"
    And the user enters the password "Test"
    And the user confirms the password "Test"
    And the user clicks the cancel button
    When the user clicks the exit button
    Then the user should be redirected to the login page

  Scenario:  Check if navigation bar can navigate to login page
    Given a user is on the registration page
    When I click on the link to the login page in the navbar LoginReg
    Then I should be redirected to the login page LoginReg

  Scenario:  Check if navigation bar can navigate to search page
    Given a user is on the registration page
    When I click on the link to the search page in the navbar LoginReg
    Then I should be redirected to the search page LoginReg

  Scenario:  Check if navigation bar can navigate to favorites page
    Given a user is on the registration page
    And I am logged in LoginReg
    When I click on the link to the favorites page in the navbar LoginReg
    Then I should be redirected to the favorites page LoginReg
