package com.sparta.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://automationexercise.com/payment")
public class PaymentPage extends PageObject {

    private final By nameOnCardField = By.name("name_on_card");
    private final By cardNumberField = By.name("card_number");
    private final By cvcField = By.name("cvc");
    private final By expiryMonthField = By.name("expiry_month");
    private final By expiryYearField = By.name("expiry_year");
    private final By payAndConfirmOrderButton = By.id("submit");

    public void enterValidPaymentDetails() {
        $(nameOnCardField).type("Kamaron Daley");
        $(cardNumberField).type("4111111111111111");
        $(cvcField).type("311");
        $(expiryMonthField).type("12");
        $(expiryYearField).type("2030");
    }

    public void enterInvalidPaymentDetails() {
        $(nameOnCardField).type("Kamaron Daley");
        $(cardNumberField).type("123");
        $(cvcField).type("1");
        $(expiryMonthField).type("00");
        $(expiryYearField).type("2000");
    }

    public void leavePaymentFieldsBlank() {
        $(nameOnCardField).clear();
        $(cardNumberField).clear();
        $(cvcField).clear();
        $(expiryMonthField).clear();
        $(expiryYearField).clear();
    }

    public void clickPayAndConfirmOrder() {
        $(payAndConfirmOrderButton)
                .waitUntilClickable()
                .click();
    }

    public boolean isPaymentPageDisplayed() {
        return getDriver()
                .getCurrentUrl()
                .contains("payment");
    }
}