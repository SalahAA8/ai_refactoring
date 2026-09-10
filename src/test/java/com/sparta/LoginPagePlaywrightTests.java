package com.sparta;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sparta.pages.LoginPagePlaywright;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class LoginPagePlaywrightTests {

    private Playwright playwright;
    private Browser browser;
    private Page page;
    private LoginPagePlaywright loginPage;

    @BeforeEach
    void beforeEach() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        page = browser.newPage();
        page.navigate("https://automationexercise.com/login");
        loginPage = new LoginPagePlaywright(page);
        loginPage.cookieAccept();
    }

    @Test
    public void checkLogin(){
        loginPage.loginFull("john234@gmail.com","Secret123@");
        assertThat(loginPage.isLoggedIn(), is(true));
    }

    @Test
    public void checkLogout(){
        loginPage.loginFull("john234@gmail.com","Secret123@");
        loginPage.clickLogout();
        assertThat(loginPage.isLoggedIn(), is(false));
    }


}
