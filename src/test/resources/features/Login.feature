Feature: Login and Logout

  As a registered user I need to log in with my correct email and password so I can access my account
  As a registered user I need to see a clear error message when my credentials are wrong so I know why my login failed
  As a user I need the system to prevent login with empty fields so invalid attempts aren't processed
  As a user I need to log out of my account so my session ends securely
  As a user I need to be redirected to the correct page after logging in so I land somewhere useful

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter a registered email and correct password
    And I click the login button
    Then I should be logged in successfully

  Scenario: Login fails with incorrect password
    Given I am on the login page
    When I enter a registered email and an incorrect password
    And I click the login button
    Then I should see the error message "Your email or password is incorrect!"

  Scenario: Login fails with an unregistered email
    Given I am on the login page
    When I enter an email address that is not registered
    And I enter any password
    And I click the login button
    Then I should see the error message "Your email or password is incorrect!"

  Scenario: Login fails when email and password fields are empty
    Given I am on the login page
    When I leave the email and password fields empty
    And I click the login button
    Then I should remain on the login page

  Scenario: Successful logout
    Given I am logged in with valid credentials
    When I click the logout button
    Then I should be redirected to the login page
