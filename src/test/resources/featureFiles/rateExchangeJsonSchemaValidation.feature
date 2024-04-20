Feature: Validate json rate exchange schema

    @Currency
    Scenario: make a GET request to exchange rate API and valiate the response josn schema
    When User send  GET request to exchange rate API
    Then User receive the status code 200
    And User validate the response body schema

