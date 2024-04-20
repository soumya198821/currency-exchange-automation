Feature: Fetch latest USD to Other Currency Exchange Rate
  Background:
    When User send  GET request to exchange rate API
    Then User receive the status code 200

  @Currency
  Scenario: make a GET request to exchange rate API and valiate success
    Then User validate the response body "success"

  @Currency
  Scenario: make a GET request to exchange rate API and valiate the response body
    Then Validate 162 currency pairs are returned by the API


  @Currency
  Scenario: make a GET request to exchange rate API and valiate the response body
    Then I validate the USD price against the following currencies:
      | Currency | PriceRangeLowerSide | PriceRangeUpperSide | Status |
      | AED      | 3.6                 | 3.7                 | true   |
      | AWG      | 1.7                 | 1.9                 | true   |
      | INR      | 83.2                | 83.9                | true   |
      | ANG      | 2.3                 | 3.4                 | false  |
#      this last value is to test failed runner test which will rerun the failed test
      | USD      | 10.1                | 11.1                | true   |
