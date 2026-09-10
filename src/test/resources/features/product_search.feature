Feature: Product search

  Scenario: Search for a product by name
    Given I am on the products page
    When I search for "dress"
    Then the "Searched Products" results are displayed
    And results relating to "dress" are shown

  Scenario: Search with no matching results
    Given I am on the products page
    When I search for "laamaa"
    Then no products are displayed
    And the page does not error

  Scenario: Search is case-insensitive
    Given I am on the products page
    When I search for "dress"
    And I record the results
    And I search for "DRESS"
    Then both searches return the same products
