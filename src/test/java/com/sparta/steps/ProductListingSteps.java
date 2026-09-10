package com.sparta.steps;

import com.sparta.CookieConsent;
import com.sparta.pages.HomePage;
import com.sparta.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@ExtendWith(SerenityJUnit5Extension.class)
public class ProductListingSteps {
    @Managed
    private HomePage homePage;

    @Managed
    private ProductsPage productsPage;

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        homePage.open();
        CookieConsent.automationExercisePopup(homePage.getDriver());
    }

    @When("I navigate to the products page")
    public void iNavigateToTheProductsPage() {
        homePage.goToProducts();
    }

    @Then("a grid of products is displayed")
    public void aGridOfProductsIsDisplayed() {
        assertThat(productsPage.isProductGridDisplayed(), is(true));
        assertThat(productsPage.getProductCount(), greaterThan(0));
    }

    @And("each product shows a name and price")
    public void eachProductShowsANameAndPrice() {
        assertThat(productsPage.everyProductHasNameAndPrice(), is(true));
    }
}
