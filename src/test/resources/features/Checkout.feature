Feature: Checkout
  @Happy
  Scenario: Customer proceeds to checkout
    Given I am logged into my account
    And I have at least one product in my shopping cart
    When I click the "Proceed To Checkout" button
    Then I should be taken to the checkout page

  @Sad
  Scenario: Customer cannot proceed to checkout with an empty cart
    Given I am logged into my account
    And my shopping cart is empty
    When I view my cart
    Then I should remain on the cart page

  @Happy
  Scenario: Customer reviews their order
    Given I am on the checkout page
    When I review my order
    Then all selected products should be displayed
    And the delivery address should be displayed
    And the billing address should be displayed

  @Sad
  Scenario: Customer sees incorrect or missing order details
    Given I am on the checkout page
    When I review my order
    Then the order details should not be missing
    And the products shown should match the items added to the cart

  @Happy
  Scenario: Customer enters payment details
    Given I am on the payment page
    When I enter valid payment details
    Then the payment details should be accepted

  @Sad
  Scenario: Customer enters missing payment details
    Given I am on the payment page
    When I leave required payment fields blank
    And I click the "Pay and Confirm Order" button
    Then the payment should not be processed
    And I should see a validation message

  @Happy
  Scenario: Customer places an order
    Given I have entered valid payment details
    When I click the "Pay and Confirm Order" button
    Then my order should be placed successfully
    And I should see a success message

  @Sad
  Scenario: Customer cannot place order with invalid payment details
    Given I am on the payment page
    When I enter invalid payment details
    And I click the "Pay and Confirm Order" button
    Then my order should not be placed
    And I should remain on the payment page

  @Happy
  Scenario: Customer views the order total
    Given I am on the checkout page
    When I review the order summary
    Then I should see the total cost of my order
    And the total should match the items in my cart

  @Sad
  Scenario: Order total is missing or incorrect
    Given I am on the checkout page
    When I review the order summary
    Then the total cost should not be missing
    And the displayed total should equal the calculated order total

