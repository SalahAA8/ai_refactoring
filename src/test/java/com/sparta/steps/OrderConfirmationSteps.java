package com.sparta.steps;

import com.sparta.CookieConsent;
import com.sparta.pages.CartPage;
import com.sparta.pages.ConfirmationPage;
import com.sparta.pages.LoginPage;
import com.sparta.pages.PaymentPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SerenityJUnit5Extension.class)
public class OrderConfirmationSteps {
    private static final String VALID_EMAIL = "john234@gmail.com";
    private static final String VALID_PASSWORD = "Secret123@";

    @Managed
    private CartPage cartPage;
    @Managed
    private ConfirmationPage confirmationPage;
    @Managed
    private LoginPage loginPage;
    @Managed
    private PaymentPage paymentPage;

    @Given("I have successfully placed my order")
    public void iHaveSuccessfullyPlacedMyOrder() {
        loginPage.getDriver().manage().deleteAllCookies();
        loginPage.open();
        CookieConsent.automationExercisePopup(loginPage.getDriver());
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);
        cartPage.addProductToCart();
        cartPage.openCart();
        cartPage.clickProceedToCheckout();
        cartPage.clickPlaceOrder();

        paymentPage.enterValidPaymentDetails();
        paymentPage.clickPayAndConfirmOrder();
    }

    @Then("I should be redirected to the order confirmation page")
    public void iShouldBeRedirectedToTheOrderConfirmationPage() {
        paymentPage.waitFor(driver -> driver.getCurrentUrl().matches("^https://automationexercise.com/payment_done/\\d+$"));
    }

    @And("I should see the message {string}")
    public void iShouldSeeTheMessage(String message) {
        assertThat(confirmationPage.getMessage(), is(message));
    }

    @Then("the total should not be different from the cart total")
    public void theTotalShouldNotBeDifferentFromTheCartTotal() {
        assertThat(cartPage.getTotalPrice(), is(cartPage.getSumPrice()));
    }
}
