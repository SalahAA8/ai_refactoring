package com.sparta.steps;

import com.sparta.CookieConsent;
import com.sparta.pages.CartPage;
import com.sparta.pages.LoginPage;
import com.sparta.pages.PaymentPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@ExtendWith(SerenityJUnit5Extension.class)
public class CheckoutSteps {
    private static final String VALID_EMAIL = "john234@gmail.com";
    private static final String VALID_PASSWORD = "Secret123@";

    @Managed
    private LoginPage loginPage;
    @Managed
    private CartPage cartPage;
    @Managed
    private PaymentPage paymentPage;

    @Given("I am logged into my account")
    public void iAmLoggedIntoMyAccount() {
        loginPage.getDriver().manage().deleteAllCookies();
        loginPage.open();
        CookieConsent.automationExercisePopup(loginPage.getDriver());
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);
    }

    @And("I have at least one product in my shopping cart")
    public void iHaveAtLeastOneProductInMyShoppingCart() {
        cartPage.addProductToCart();
        cartPage.openCart();

        assertThat(cartPage.isProductInCart(), is(true));
    }

    @When("I click the {string} button")
    public void iClickTheButton(String buttonName) {
        if (buttonName.equals("Proceed To Checkout")) {
            cartPage.clickProceedToCheckout();
        } else if (buttonName.equals("Pay and Confirm Order")) {
            paymentPage.clickPayAndConfirmOrder();
        }
    }

    @Then("I should be taken to the checkout page")
    public void iShouldBeTakenToTheCheckoutPage() {
        assertThat(
                cartPage.getDriver().getCurrentUrl(),
                containsString("checkout")
        );
    }

    @And("my shopping cart is empty")
    public void myShoppingCartIsEmpty() {
        cartPage.open();
    }

    @When("I view my cart")
    public void iViewMyCart() {
        cartPage.open();
    }

    @Then("I should remain on the cart page")
    public void iShouldRemainOnTheCartPage() {
        assertThat(
                cartPage.getDriver().getCurrentUrl(),
                containsString("view_cart")
        );
    }

    @Given("I am on the checkout page")
    public void iAmOnTheCheckoutPage() {
        iAmLoggedIntoMyAccount();
        cartPage.open();
        cartPage.addProductToCart();
        cartPage.openCart();
        cartPage.clickProceedToCheckout();

        assertThat(cartPage.isOnCheckoutPage(), is(true));
    }

    @When("I review my order")
    public void iReviewMyOrder() {
        assertThat(cartPage.isOnCheckoutPage(), is(true));
    }

    @Then("all selected products should be displayed")
    public void allSelectedProductsShouldBeDisplayed() {
        assertThat(cartPage.areSelectedProductsDisplayed(), is(true));
    }

    @Then("the delivery address should be displayed")
    public void theDeliveryAddressShouldBeDisplayed() {
        assertThat(cartPage.isDeliveryAddressDisplayed(), is(true));
    }

    @Then("the billing address should be displayed")
    public void theBillingAddressShouldBeDisplayed() {
        assertThat(cartPage.isBillingAddressDisplayed(), is(true));
    }

    @Then("the order details should not be missing")
    public void theOrderDetailsShouldNotBeMissing() {
        assertThat(cartPage.areSelectedProductsDisplayed(), is(true));
        assertThat(cartPage.isDeliveryAddressDisplayed(), is(true));
        assertThat(cartPage.isBillingAddressDisplayed(), is(true));
    }

    @Then("the products shown should match the items added to the cart")
    public void theProductsShownShouldMatchTheItemsAddedToTheCart() {
        assertThat(cartPage.areSelectedProductsDisplayed(), is(true));
    }

    @Given("I am on the payment page")
    public void iAmOnThePaymentPage() {
        iAmOnTheCheckoutPage();
        cartPage.clickPlaceOrder();

        assertThat(paymentPage.isPaymentPageDisplayed(), is(true));
    }

    @When("I enter valid payment details")
    public void iEnterValidPaymentDetails() {
        paymentPage.enterValidPaymentDetails();
    }

    @Then("the payment details should be accepted")
    public void thePaymentDetailsShouldBeAccepted() {
        paymentPage.clickPayAndConfirmOrder();

        WebDriverWait wait = new WebDriverWait(
                paymentPage.getDriver(),
                Duration.ofSeconds(10)
        );

        wait.until(driver ->
                driver.getCurrentUrl().contains("payment_done")
        );

        assertThat(
                paymentPage.getDriver().getCurrentUrl(),
                containsString("payment_done")
        );
    }

    @When("I leave required payment fields blank")
    public void iLeaveRequiredPaymentFieldsBlank() {
        paymentPage.leavePaymentFieldsBlank();
    }

    @Then("the payment should not be processed")
    public void thePaymentShouldNotBeProcessed() {
        assertThat(paymentPage.isPaymentPageDisplayed(), is(true));
    }

    @Then("I should see a validation message")
    public void iShouldSeeAValidationMessage() {
        assertThat(paymentPage.isPaymentPageDisplayed(), is(true));
    }

    @Given("I have entered valid payment details")
    public void iHaveEnteredValidPaymentDetails() {
        iAmOnThePaymentPage();
        paymentPage.enterValidPaymentDetails();
    }

    @Then("my order should be placed successfully")
    public void myOrderShouldBePlacedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(
                paymentPage.getDriver(),
                Duration.ofSeconds(10)
        );

        wait.until(driver ->
                driver.getCurrentUrl().contains("payment_done")
        );

        assertThat(
                paymentPage.getDriver().getCurrentUrl(),
                containsString("payment_done")
        );
    }

    @Then("I should see a success message")
    public void iShouldSeeASuccessMessage() {
        assertThat(
                paymentPage.getDriver().getTitle(),
                containsString("Order Placed")
        );
    }

    @When("I enter invalid payment details")
    public void iEnterInvalidPaymentDetails() {
        paymentPage.enterInvalidPaymentDetails();
    }

    @Then("my order should not be placed")
    public void myOrderShouldNotBePlaced() {
        assertThat(
                paymentPage.getDriver().getCurrentUrl(),
                containsString("payment")
        );
    }

    @Then("I should remain on the payment page")
    public void iShouldRemainOnThePaymentPage() {
        assertThat(paymentPage.isPaymentPageDisplayed(), is(true));
    }

    @When("I review the order summary")
    public void iReviewTheOrderSummary() {
        assertThat(cartPage.isOnCheckoutPage(), is(true));
    }

    @Then("I should see the total cost of my order")
    public void iShouldSeeTheTotalCostOfMyOrder() {
        assertThat(cartPage.isTotalAmountDisplayed(), is(true));
    }

    @Then("the total should match the items in my cart")
    public void theTotalShouldMatchTheItemsInMyCart() {
        assertThat(
                cartPage.getDisplayedOrderTotal(),
                is(cartPage.calculateOrderTotalFromPriceAndQuantity())
        );
    }

    @Then("the total cost should not be missing")
    public void theTotalCostShouldNotBeMissing() {
        assertThat(cartPage.isTotalAmountDisplayed(), is(true));
        assertThat(cartPage.getDisplayedOrderTotal(), greaterThan(0));
    }

    @Then("the displayed total should equal the calculated order total")
    public void theDisplayedTotalShouldEqualTheCalculatedOrderTotal() {
        assertThat(
                cartPage.getDisplayedOrderTotal(),
                is(cartPage.calculateOrderTotalFromDisplayedItemTotals())
        );
    }
}