package com.sparta.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPagePlaywright {
    private final Page page;
    private final Locator emailField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator logoutButton;
    private final Locator loggedInIndicator;
    private final Locator consentButton;
    private final Locator errorMessage;

    public LoginPagePlaywright(Page page) {
        this.page = page;
        emailField = page.locator("input[data-qa='login-email']");
        passwordField = page.locator("input[data-qa='login-password']");
        loginButton = page.locator("button[data-qa='login-button']");
        logoutButton = page.locator("a[href='/logout']");
        loggedInIndicator = page.locator("//a[contains(text(),'Logged in as')]");
        consentButton = page.locator("button:has-text('Consent')");
        errorMessage = page.locator(".login-form p");
    }

    public void clickLogin() {
        loginButton.click();
    }
    public void clickLogout() {
        logoutButton.click();
    }

    public void typeEmail(String email) {
        emailField.clear();
        emailField.fill(email); 
    }

    public void typePassword(String password) {
        passwordField.clear();
        passwordField.fill(password);
    }

    public boolean isLoggedIn() {
        return loggedInIndicator.isVisible();
    }
    public void cookieAccept(){
        if (consentButton.isVisible()) {
            consentButton.click();
        }
    }

    public void loginFull(String email, String password) {
        typeEmail(email);
        typePassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }
}
