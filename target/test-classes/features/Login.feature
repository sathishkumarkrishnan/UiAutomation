Feature: Login Functionality on The Internet App

  Scenario: Login with valid credentials
    Given I open the login page
    When I enter the valid username and password
    Then I should see the secure area

  Scenario: Login with invalid credentials
    Given I open the login page
    When I enter an invalid username and password
    Then I should see an error message
