package com.sparta.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://automationexercise.com/login")
public class LoginPage extends PageObject {
        @FindBy(css = "input[data-qa='login-email']")
        private WebElementFacade emailField;

        @FindBy(css = "input[data-qa='login-password']")
        private WebElementFacade passwordField;

        @FindBy(css = "button[data-qa='login-button']")
        private WebElementFacade loginButton;

        @FindBy(css = ".login-form p")
        private WebElementFacade errorMessage;

        @FindBy(css = "a[href='/logout']")
        private WebElementFacade logoutButton;

        @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
        private WebElementFacade loggedInIndicator;

        public void typeEmail(String email) {
            emailField.sendKeys(email);
        }

        public void typePassword(String password) {
            passwordField.sendKeys(password);
        }

        public void clickLogin() {
            loginButton.click();
        }

        public String getErrorMessage() {
            return errorMessage.getText();
        }

        public boolean isErrorMessageDisplayed() {
            return errorMessage.isVisible();
        }

        public void clickLogout() {
            logoutButton.click();
        }

        public boolean isLoggedIn() {
            return loggedInIndicator.isVisible();
        }

        public String getLoggedInUsername() {
            return loggedInIndicator.getText();
        }

        public void login(String email, String password) {
            typeEmail(email);
            typePassword(password);
            clickLogin();
        }

}
