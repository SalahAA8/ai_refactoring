package com.sparta.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@DefaultUrl("https://automationexercise.com/products")
public class ProductsPage extends PageObject {
    @FindBy(css = ".features_items")
    private WebElementFacade productGrid;

    @FindBy(css = ".features_items .product-image-wrapper")
    private List<WebElementFacade> productCards;

    @FindBy(id = "search_product")
    private WebElementFacade searchBox;

    @FindBy(id = "submit_search")
    private WebElementFacade searchButton;

    @FindBy(css = ".features_items > .title")
    private WebElementFacade resultsTitle;

    @FindBy(css = ".brands_products")
    private WebElementFacade brandsSidebar;

    public boolean isProductGridDisplayed() {
        return productGrid.isVisible();
    }

    public void searchFor(String term) {
        searchBox.clear();
        searchBox.sendKeys(term);
        searchButton.click();
    }

    public String getResultsTitle() {
        return resultsTitle.getText();
    }

    public boolean isSearchBoxDisplayed() {
        return searchBox.isVisible();
    }

    public List<String> getProductNames() {
        return productCards.stream()
                .map(card -> card.findBy(".productinfo p").getText())
                .toList();
    }

    public int getProductCount() {
        return productCards.size();
    }

    public List<String> getProductIds() {
        return productCards.stream()
                .map(card -> card.findElement(By.cssSelector("a[data-product-id]"))
                        .getAttribute("data-product-id"))
                .toList();
    }

    public boolean everyProductHasNameAndPrice() {
        return productCards.stream().allMatch(card ->
                !card.findBy(".productinfo p").getText().isBlank()
                        && card.findBy(".productinfo h2").getText().contains("Rs."));
    }

    public boolean isBrandsSidebarVisible() {
        return brandsSidebar.isVisible();
    }

    public List<WebElementFacade> getBrandLinks() {
        return findAll(By.xpath("//div[@class='brands_products']//a"));
    }

    /**
     * Extracts the brand name from the link's href rather than its visible text,
     * since the visible text also includes the "(count)" badge span
     * e.g. <a href="/brand_products/Polo"><span>(6)</span>Polo</a>
     * getText() would return "(6)Polo" — the href gives a clean "Polo".
     */
    public String getBrandName(int index) {
        String href = getBrandLinks().get(index).getAttribute("href");
        String encodedName = href.substring(href.lastIndexOf('/') + 1);
        return URLDecoder.decode(encodedName, StandardCharsets.UTF_8);
    }

    public void clickBrand(int index) {
        getBrandLinks().get(index).click();
    }
}
