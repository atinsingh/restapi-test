Feature: Check user login
  Scenario: User try to generate token with invalid user pass
    Given user can access api url "https://app.pragra.io/"
    When he call end point "/api/authenticate" with user "user" and password "user"
    Then api should return with status code 400
    And message should "Invalid login name or password."
