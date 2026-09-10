Feature: View Category Products

  @Happy
  Scenario: View products under a category and switch to another category
    Given I am on the Automation Exercise home page
    Then categories should be visible on the left sidebar
    When I click on the "Women" category
    And I click on the "Dress" sub-category link
    Then the category page should be displayed
    And the category title should contain "WOMEN"
    When I click on the "Tshirts" sub-category link under the "Men" category
    Then the category page should be displayed
    And the category title should contain "MEN"

  @Sad
  Scenario: Navigating directly to a non-existent category does not crash the page
    Given I am on the Automation Exercise home page
    When I navigate directly to category id 9999
    Then the category page should not show a server error
    And the category page should still render normally