package com.sparta.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://automationexercise.com/product_details/{1}")
public class ProductPage extends PageObject {
    @FindBy(css = ".product-information > h2")
    private WebElementFacade name;
    @FindBy(css = ".product-information > p")
    private WebElementFacade category;
    @FindBy(css = ".product-information > img")
    private WebElementFacade rating;

    @FindBy(css = ".product-information button")
    private WebElementFacade addToCart;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[2]")
    private WebElementFacade availability;
    @FindBy(xpath = "/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[3]")
    private WebElementFacade condition;
    @FindBy(xpath = "/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[4]")
    private WebElementFacade brand;

    @FindBy(id = "name")
    private WebElementFacade nameField;
    @FindBy(id = "email")
    private WebElementFacade emailField;
    @FindBy(id = "review")
    private WebElementFacade reviewField;
    @FindBy(id = "button-review")
    private WebElementFacade submitReviewButton;
    @FindBy(id = "review-section")
    private WebElementFacade submitReviewMessage;

    public void open(Integer id) {
        open(withParameters(id.toString()));
    }

    public String getName() {
        return name.getText();
    }

    public String getCategory() {
        return category.getText();
    }

    public WebElementFacade getRating() {
        return rating;
    }

    public void addToCart() {
        addToCart.click();
    }

    public String getAvailability() {
        return availability.getText();
    }

    public String getCondition() {
        return condition.getText();
    }

    public String getBrand() {
        return brand.getText();
    }

    public void typeReviewName(String name) {
        nameField.sendKeys(name);
    }

    public void typeReviewEmail(String email) {
        emailField.sendKeys(email);
    }

    public void typeReviewContent(String review) {
        reviewField.sendKeys(review);
    }

    public void submitReview() {
        submitReviewButton.click();
    }

    public String getReviewSubmitMessage() {
        return submitReviewMessage.getText();
    }
}
