package com.sparta.steps;

import com.sparta.CookieConsent;
import com.sparta.pages.BrandPage;
import com.sparta.pages.HomePage;
import com.sparta.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class BrandSteps {

    @Managed
    private HomePage homePage;

    @Managed
    private ProductsPage productsPage;

    @Managed
    private BrandPage brandPage;

    private String lastSelectedBrandName;

    @When("I click on the {string} button")
    public void iClickOnTheButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Products")) {
            homePage.clickProductsButton();
        }
    }

    @Then("brands should be visible on the left sidebar")
    public void brandsShouldBeVisibleOnTheLeftSidebar() {
        assertThat(productsPage.isBrandsSidebarVisible(), is(true));
    }

    @When("I select the first available brand")
    public void iSelectTheFirstAvailableBrand() {
        lastSelectedBrandName = productsPage.getBrandName(0);
        productsPage.clickBrand(0);
    }

    @When("I select the second available brand")
    public void iSelectTheSecondAvailableBrand() {
        lastSelectedBrandName = productsPage.getBrandName(1);
        productsPage.clickBrand(1);
    }

    @Then("I should be navigated to that brand's page")
    public void iShouldBeNavigatedToThatBrandsPage() {
        assertThat(brandPage.getTitleText(), containsString(lastSelectedBrandName));
    }

    @And("products should be displayed for that brand")
    public void productsShouldBeDisplayedForThatBrand() {
        assertThat(brandPage.getProductCount(), greaterThan(0));
    }

    // ---- Sad path ----

    @When("I navigate directly to the brand {string}")
    public void iNavigateDirectlyToTheBrand(String brandName) {
        brandPage.openBrandPage(brandName); // Updated here
        CookieConsent.automationExercisePopup(brandPage.getDriver());
    }
    @Then("the brand page should not show a server error")
    public void theBrandPageShouldNotShowAServerError() {
        assertThat(brandPage.showsServerError(), is(false));
    }

    @And("the brand page should still render normally")
    public void theBrandPageShouldStillRenderNormally() {
        assertThat(brandPage.isPageRenderedNormally(), is(true));
    }
}