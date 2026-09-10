Feature: Order Confirmation

  As a customer, I want to receive an order confirmation after payment so that I know my purchase has been completed successfully.
  - The system displays an order confirmation page after a successful purchase.
  - The confirmation page includes the customer's order details.
  - The confirmation page confirms that the order has been placed successfully.
  - The customer can view the confirmation immediately after completing the checkout process.
  - The confirmation is displayed only after the order has been processed successfully.

  @HappyPath
  Scenario: Customer receives an order confirmation
    Given I have successfully placed my order
    Then I should be redirected to the order confirmation page
    And I should see the message "Congratulations! Your order has been confirmed!"

  @SadPath
  Scenario: Customer sees an incorrect order total
    Given I am on the checkout page
    Then the total should not be different from the cart total
