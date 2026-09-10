package com.sparta.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://automationexercise.com/")
public class HomePage extends PageObject {
    @FindBy(css = "a[href='/products']")
    private WebElementFacade productsNavLink;

    @FindBy(css = ".left-sidebar")
    private WebElementFacade categorySidebar;

    @FindBy(xpath = "//a[@href='/products']")
    private WebElementFacade productsButton;

    public void goToProducts() {
        productsNavLink.click();
    }

    public boolean isCategorySidebarVisible() {
        return categorySidebar.isVisible();
    }

    public void clickCategory(String categoryName) {
        $(By.xpath("//a[@href='#" + categoryName + "']")).click();
    }

    public void clickSubCategory(String parentCategory, String subCategoryName) {
        $(By.xpath("//div[@id='" + parentCategory + "']//a[contains(text(),'" + subCategoryName + "')]")).click();
    }

    public void clickProductsButton() {
        productsButton.click();
    }
}
