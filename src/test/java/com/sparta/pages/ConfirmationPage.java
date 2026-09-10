package com.sparta.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://automationexercise.com/payment_done")
public class ConfirmationPage extends PageObject {
    @FindBy(xpath = "/html/body/section/div/div/div/p")
    private WebElementFacade message;

    @FindBy(className = "check_out")
    private WebElementFacade downloadInvoiceButton;
    @FindBy(css = "a[href=\"/\"]")
    private WebElementFacade continueButton;

    public String getMessage() {
        return message.getTextContent();
    }

    public void clickDownloadInvoice() {
        downloadInvoiceButton.click();
    }

    public void clickContinue() {
        continueButton.click();
    }
}
