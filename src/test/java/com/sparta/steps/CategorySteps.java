package com.sparta.steps;

import com.sparta.CookieConsent;
import com.sparta.pages.CategoryPage;
import com.sparta.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class CategorySteps {

    @Managed
    private HomePage homePage;

    @Managed
    private CategoryPage categoryPage;

    @Given("I am on the Automation Exercise home page")
    public void iAmOnTheAutomationExerciseHomePage() {
        homePage.open();
        CookieConsent.automationExercisePopup(homePage.getDriver());
    }

    @Then("categories should be visible on the left sidebar")
    public void categoriesShouldBeVisibleOnTheLeftSidebar() {
        assertThat(homePage.isCategorySidebarVisible(), is(true));
    }

    @When("I click on the {string} category")
    public void iClickOnTheCategory(String category) {
        homePage.clickCategory(category);
    }

    @And("I click on the {string} sub-category link")
    public void iClickOnTheSubCategoryLink(String subCategory) {
        homePage.clickSubCategory("Women", subCategory);
    }

    @When("I click on the {string} sub-category link under the {string} category")
    public void iClickOnTheSubCategoryLinkUnderTheCategory(String subCategory, String parentCategory) {
        homePage.clickCategory(parentCategory);
        homePage.clickSubCategory(parentCategory, subCategory);
    }

    @Then("the category page should be displayed")
    public void theCategoryPageShouldBeDisplayed() {
        assertThat(categoryPage.isTitleVisible(), is(true));
    }

    @And("the category title should contain {string}")
    public void theCategoryTitleShouldContain(String expectedText) {
        assertThat(categoryPage.getTitleText().toUpperCase(), containsString(expectedText.toUpperCase()));
    }

    // ---- Sad path ----

    @When("I navigate directly to category id {int}")
    public void iNavigateDirectlyToCategoryId(int id) {
        categoryPage.open(id);
        CookieConsent.automationExercisePopup(categoryPage.getDriver());
    }

    @Then("the category page should not show a server error")
    public void theCategoryPageShouldNotShowAServerError() {
        assertThat(categoryPage.showsServerError(), is(false));
    }

    @And("the category page should still render normally")
    public void theCategoryPageShouldStillRenderNormally() {
        assertThat(categoryPage.isPageRenderedNormally(), is(true));
    }
}