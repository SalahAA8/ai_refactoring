package com.sparta.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://automationexercise.com/category_products/{1}")
public class CategoryPage extends PageObject {

    @FindBy(css = "h2.title.text-center")
    private WebElementFacade title;

    @FindBy(css = ".left-sidebar")
    private WebElementFacade sidebar;

    public void open(Integer id) {
        open(withParameters(id.toString()));
    }

    public boolean isTitleVisible() {
        return title.isVisible();
    }

    public String getTitleText() {
        return title.getText();
    }

    public int getProductCount() {
        return findAll(By.className("product-image-wrapper")).size();
    }

    /**
     * Used by sad-path scenarios: confirms the page still rendered the normal
     * site layout (header/sidebar) rather than crashing into a blank or raw
     * error page, regardless of whether the category itself has products.
     */
    public boolean isPageRenderedNormally() {
        return sidebar.isCurrentlyVisible();
    }

    public boolean showsServerError() {
        String bodyText = getDriver().findElement(By.tagName("body")).getText().toLowerCase();
        return bodyText.contains("500")
                || bodyText.contains("internal server error")
                || bodyText.contains("whitelabel error");
    }
}