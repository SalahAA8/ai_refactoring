package com.sparta.steps;


import com.sparta.pages.CartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class CartSteps {
@Managed
CartPage cartPage;

    @When("I click add to cart")
    public void iClickAddToCart() {
        cartPage.clickAddToCartButton();
    }

    @Then("I should see a confirmation message")
    public void theCustomerSeesAConfirmationMessage() {
        assertThat(cartPage.confirmationMessage(), containsString("has been added to cart"));
    }

    @Then("the product is added to cart")
    public void theProductIsAddedToCart() {
        cartPage.clickViewCartButton();
        assertThat(cartPage.isProductInCart(), is(true));
    }


    @And("I have added items into the cart")
    public void iHaveAddedItemsIntoTheCart() {
        cartPage.clickAddToCartButton();
    }

    @When("I remove a product from the cart")
    public void iRemoveAProductFromTheCart() {
        cartPage.clickViewCartButton();
        cartPage.clickDeleteButton();
    }

    @Then("the product should no longer appear in the cart")
    public void theProductShouldNoLongerAppearInTheCart() {
        assertThat(cartPage.isCartEmpty(), is(true));
    }

    @When("I update the item amount")
    public void iUpdateTheItemAmount() {
        cartPage.clickViewCartButton();
        cartPage.clickQuantityButton();
    }

    @Then("the cart should display the new quantity")
    public void theCartShouldDisplayTheNewQuantity() {
        assertThat(cartPage.getQuantity(), is(2));
    }
}
