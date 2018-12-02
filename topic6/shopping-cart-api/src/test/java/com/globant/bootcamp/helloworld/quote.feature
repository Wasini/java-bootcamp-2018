Feature: Quote generator

  Background:
    * url BaseUrl

  Scenario: Fetch random quote

    Given path '/quote'
    When method get
    Then status 200
    And match $ == {quote:'#notnull'}