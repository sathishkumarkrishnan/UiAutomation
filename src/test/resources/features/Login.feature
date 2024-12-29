Feature: Login Functionality on The Internet App

  Scenario Outline: Login with valid credentials
    Given I open the login page
    When I enter username "<username>" and password "<password>"
    Then I should see the secure area

   	Examples:
      | username           | password       |
      | tomsmith  | SuperSecretPassword!    |
      | user2@example.com  | qwerty456      |