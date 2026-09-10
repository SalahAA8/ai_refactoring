Feature: Adding, removing, updating cart, saved cart

  @Happy
  Scenario: Add a product to cart
    Given I am on the products page
    When I click add to cart
    Then I should see a confirmation message
    And the product is added to cart


  @Happy
  Scenario: Remove product from cart
    Given I am on the products page
    And I have added items into the cart
    When I remove a product from the cart
    Then the product should no longer appear in the cart

  @Happy
  Scenario: Update item quantity from cart page
    Given I am on the products page
    And I have added items into the cart
    When I update the item amount
    Then the cart should display the new quantity


