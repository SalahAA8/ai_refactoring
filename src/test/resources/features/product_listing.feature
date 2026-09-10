Feature: Product listing

  Scenario: View all products
    Given I am on the home page
    When I navigate to the products page
    Then a grid of products is displayed
    And each product shows a name and price
