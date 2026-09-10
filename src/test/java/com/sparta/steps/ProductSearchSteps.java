package com.sparta.steps;

import com.sparta.CookieConsent;
import com.sparta.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@ExtendWith(SerenityJUnit5Extension.class)
public class ProductSearchSteps {
    @Managed
    private ProductsPage productsPage;

    private List<String> recordedProductIds;

    @Given("I am on the products page")
    public void iAmOnTheProductsPage() {
        productsPage.open();
        CookieConsent.automationExercisePopup(productsPage.getDriver());
    }

    @When("I search for {string}")
    public void iSearchFor(String term) {
        productsPage.searchFor(term);
    }

    @Then("the {string} results are displayed")
    public void theResultsAreDisplayed(String title) {
        assertThat(productsPage.getResultsTitle(), containsString(title.toUpperCase()));
    }

    @And("results relating to {string} are shown")
    public void resultsRelatingToAreShown(String term) {
        List<String> allNames = productsPage.getProductNames();
        assertThat("No products were returned", allNames, is(not(empty())));
        assertThat("No result name contained '" + term + "' | results: " + allNames,
                allNames.stream().anyMatch(name -> name.toLowerCase().contains(term.toLowerCase())),
                is(true));
    }

    @Then("no products are displayed")
    public void noProductsAreDisplayed() {
        assertThat(productsPage.getProductCount(), is(0));
    }

    @And("the page does not error")
    public void thePageDoesNotError() {
        assertThat(productsPage.isSearchBoxDisplayed(), is(true));
    }

    @And("I record the results")
    public void iRecordTheResults() {
        recordedProductIds = productsPage.getProductIds();
    }

    @Then("both searches return the same products")
    public void bothSearchesReturnTheSameProducts() {
        assertThat(productsPage.getProductIds(), is(equalTo(recordedProductIds)));
    }
}
