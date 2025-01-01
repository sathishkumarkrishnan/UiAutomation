Feature: Login functionality

  Scenario Outline: Login with multiple credentials
    Given I open the login page
    When I enter username "<username>" and password "<password>"
    Then I should see an error message

  Examples:
    | username     | password         |
    | <username>   | <password>       |
