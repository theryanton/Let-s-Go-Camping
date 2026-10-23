Feature: Login
  Login page for users.
  If the user doesn't already have an account, they should be able to navigate to the registration page from here.
  If the user enters an existing username and the corresponding password, they should be successfully logged in and
  redirected to the home page.
  If the user does not enter an existing username, they should see the error 'Invalid username'.
  If the user enters the incorrect password, they should see the error 'Incorrect password'.
  If the user fails to login successfully three consecutive times within 30 seconds,
  they should be redirected to an account blocked page.

  Scenario: Successful Login
    Given a user is on the login page
    And the user enters the username "ABC"
    And the user enters the password "Ab9"
    When the user clicks the login button
    Then I should be redirected to the search page LoginReg

  Scenario: Invalid Username
    Given a user is on the login page
    And the user enters an invalid username
    And the user enters the password "Ab10"
    When the user clicks the login button
    Then error "Invalid username" is returned

  Scenario: Incorrect Password
    Given a user is on the login page
    And the user enters the username "ABC"
    And the user enters an incorrect password
    When the user clicks the login button
    Then error "Incorrect password" is returned

  Scenario:  Check if navigation bar can navigate to registration page
    Given a user is on the login page
    When I click on the link to the registration page in the navbar LoginReg
    Then I should be redirected to the registration page LoginReg

  Scenario:  Check if navigation bar can navigate to search page
    Given a user is on the login page
    When I click on the link to the search page in the navbar LoginReg
    Then I should be redirected to the search page LoginReg

  Scenario:  Check if navigation bar can navigate to favorites page
    Given a user is on the login page
    And I am logged in LoginReg
    When I click on the link to the favorites page in the navbar LoginReg
    Then I should be redirected to the favorites page LoginReg
