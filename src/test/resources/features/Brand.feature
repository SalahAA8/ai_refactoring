Feature: View and Cart Brand Products
  @Happy
  Scenario: View products for a brand and switch to another brand
    Given I am on the Automation Exercise home page
    When I click on the "Products" button
    Then brands should be visible on the left sidebar
    When I select the first available brand
    Then I should be navigated to that brand's page
    And products should be displayed for that brand
    When I select the second available brand
    Then I should be navigated to that brand's page
    And products should be displayed for that brand

  @Sad
  Scenario: Navigating directly to a non-existent brand does not crash the page
    Given I am on the Automation Exercise home page
    When I navigate directly to the brand "NotARealBrandXYZ"
    Then the brand page should not show a server error
    And the brand page should still render normally