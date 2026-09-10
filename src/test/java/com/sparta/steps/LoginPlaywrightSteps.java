package com.sparta.steps;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.sparta.pages.LoginPagePlaywright;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginPlaywrightSteps {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private LoginPagePlaywright loginPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        page = browser.newPage();
        page.navigate("https://automationexercise.com/login");
        loginPage = new LoginPagePlaywright(page);
        loginPage.cookieAccept();
    }

    @When("I enter a registered email and correct password")
    public void iEnterARegisteredEmailAndCorrectPassword() {
        loginPage.typeEmail("john234@gmail.com");
        loginPage.typePassword("Secret123@");
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        assertThat(loginPage.isLoggedIn(), is(true));
    }

    @When("I enter a registered email and an incorrect password")
    public void iEnterARegisteredEmailAndAnIncorrectPassword() {
        loginPage.typeEmail("john234@gmail.com");
        loginPage.typePassword("incorrectpassword");
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedErrorMessage) {
        assertThat(loginPage.getErrorMessage(), containsString(expectedErrorMessage));
    }

    @When("I enter an email address that is not registered")
    public void iEnterAnEmailAddressThatIsNotRegistered() {
        loginPage.typeEmail("john@gmail.com");
    }

    @And("I enter any password")
    public void iEnterAnyPassword() {
        loginPage.typePassword("password");
    }

    @When("I leave the email and password fields empty")
    public void iLeaveTheEmailAndPasswordFieldsEmpty() {
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        String currentUrl = page.url();
        assertThat(currentUrl, is("https://automationexercise.com/login"));
    }

    @Given("I am logged in with valid credentials")
    public void iAmLoggedInWithValidCredentials() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        page = browser.newPage();
        page.navigate("https://automationexercise.com/login");
        loginPage = new LoginPagePlaywright(page);
        loginPage.cookieAccept();
        loginPage.loginFull("john234@gmail.com","Secret123@");
    }

    @When("I click the logout button")
    public void iClickTheLogoutButton() {
        loginPage.clickLogout();
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        String currentUrl = page.url();
        assertThat(currentUrl, is("https://automationexercise.com/login"));
    }
}
