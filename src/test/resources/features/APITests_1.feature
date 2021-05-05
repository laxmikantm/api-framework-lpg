Feature: Cities API Test
  Description: Here we test the High level API health for the site

  @api_test
  Scenario: Smoke test - Get All cities
    Given I use "Citys" API end point
    When I make a GET request
    Then I get success response 200 code
    And response contains correct structure for the cities end point response
    And response has the cities
    |London|New York|Los Angeles|Sydney|Paris|

  @api_test
  Scenario: Smoke test - attractions end point
    Given I use "Attractions" API end point
    When I make a GET request
    Then I get success response 200 code

  @api_test
  Scenario: Smoke test - Get All attractions for New York
    Given I use "Attractions" API end point
    And I search for all attractions for "New York"
    When I make a GET request
    Then I get success response 200 code

  @api_test
  Scenario: Smoke test - Get All attractions for New York
    Given I use "Attractions" API end point
    And I search for all attractions for "New York"
    And with attraction type as "Museum"
    When I make a GET request
    Then I get success response 200 code