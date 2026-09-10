Feature: Product Page

  As a customer, when I view an individual products page I need to see and create reviews so I can identify and share the quality of the product
  As a customer I need to see information about each product (availability, condition, brand) so that I can make an informed decision
  As a customer I need to see the category of each product so I can identify similar items

  @HappyPath
  Scenario: Product rating
    Given I am on the page for product 1
    Then I should be able to see the average score of the product

  @HappyPath
  Scenario: Can write a review
    Given I am on the page for product 1
    When I enter "John Wayne" into the review name field
    And I enter "jwayne@example.com" into review email field
    And I enter "It was good" into the review text field
    And I press submit
    Then the message "Thank you for your review." should appear

  @HappyPath
  Scenario: Product Information
    Given I am on the page for product 1
    Then I should be able to see the availability of the product
    And I should be able to see the condition of the product
    And I should be able to see the brand of the product

  @HappyPath
  Scenario: Product Category
    Given I am on the page for product 1
    Then I should be able to see the category of the product
