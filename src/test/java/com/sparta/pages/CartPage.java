package com.sparta.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

import java.util.List;


@DefaultUrl("https://automationexercise.com/view_cart")
public class CartPage extends PageObject {
    @FindBy(xpath = "//a[contains(text(), 'Products')]")
    private WebElementFacade productsButton;
    @FindBy(xpath = "(//a[contains(text(), 'Add to cart')])[1]")
    private WebElementFacade addFirstProductButton;
    @FindBy(xpath = "//button[contains(text(), 'Continue Shopping')]")
    private WebElementFacade continueShoppingButton;
    @FindBy(xpath = "//a[contains(text(), 'Cart')]")
    private WebElementFacade cartButton;
    @FindBy(xpath = "//a[contains(text(), 'Proceed To Checkout')]")
    private WebElementFacade proceedToCheckoutButton;
    @FindBy(xpath = "cart_info")
    private WebElementFacade cartTable;
    @FindBy(xpath = "//a[contains(text(), 'Place Order')]")
    private WebElementFacade placeOrderButton;
    @FindBy(id = "address_delivery")
    private WebElementFacade deliveryAddress;
    @FindBy(id = "address_invoice")
    private WebElementFacade billingAddress;
    @FindBy(className = "cart_total_price")
    private List<WebElementFacade> prices;
    @FindBy(css = "#cart_info tbody tr:not(:last-child) .cart_price p")
    private List<WebElementFacade> itemPrices;
    @FindBy(css = "#cart_info tbody tr:not(:last-child) .cart_quantity button")
    private List<WebElementFacade> itemQuantities;
    @FindBy(css = "#cart_info tbody tr:not(:last-child) .cart_total_price")
    private List<WebElementFacade> displayedItemTotals;
    @FindBy(css = "#cart_info tbody tr:last-child .cart_total_price")
    private WebElementFacade totalAmount;

    @FindBy(css = "a.add-to-cart")
    private WebElementFacade addToCartButton;

    @FindBy(css = "#cartModal a[href='/view_cart']")
    private WebElementFacade viewCartButton;

    @FindBy(css = "button.disabled")
    private WebElementFacade quantityButton;

    @FindBy(css = "button.check_out")
    private WebElementFacade checkOutButton;

    @FindBy(css = "p.cart_total_price")
    private WebElementFacade totalPrice;

    @FindBy(css = "a.cart_quantity_delete")
    private WebElementFacade deleteButton;

    @FindBy(css= "#cartModal .modal-body p.text-center")
    private WebElementFacade confirmationMessage;

    private final By emptyCartMessage = By.id("empty_cart");
    private final By cartRows = By.cssSelector("#cart_info_table tbody tr");
    private final By addSecondProductButton = By.xpath("(//a[contains(text(), 'Add to cart')])[2]");

    public void addProductToCart() {
        productsButton.click();
        addFirstProductButton.click();
        continueShoppingButton.click();
    }
    public void addSecondProductToCart() {
        $(productsButton).click();
        $(addSecondProductButton).click();
        $(continueShoppingButton).click();
    }

    public void openCart() {
        cartButton.click();
    }

    public void clickProceedToCheckout() {
        proceedToCheckoutButton.click();
    }

    public boolean isProductInCart() {
        return cartTable.isVisible();
    }

    public boolean isOnCheckoutPage() {
        return getDriver().getCurrentUrl().contains("checkout");
    }

    public boolean areSelectedProductsDisplayed() {
        return cartTable.isVisible();
    }

    public boolean isDeliveryAddressDisplayed() {
        return deliveryAddress.isVisible();
    }

    public boolean isBillingAddressDisplayed() {
        return billingAddress.isVisible();
    }

    public void clickPlaceOrder() {
        placeOrderButton.click();
    }

    public int getTotalPrice() {
        var priceString = prices.getLast().getText().substring(4);
        return Integer.parseInt(priceString);
    }

    public int getSumPrice() {
        var priceStrings = prices.reversed().stream().skip(1).map(el -> el.getText().substring(4));
        return priceStrings.map(Integer::parseInt).reduce(0, Integer::sum);
    }

    public boolean isTotalAmountDisplayed() {
        return totalAmount.isVisible();
    }

    public int getDisplayedOrderTotal() {
        return extractNumber(totalAmount.getText());
    }

    public int calculateOrderTotalFromPriceAndQuantity() {
        if (prices.isEmpty() || prices.size() != itemQuantities.size()) {
            throw new IllegalStateException(
                    "Could not calculate total because the item prices and quantities did not match."
            );
        }

        int calculatedTotal = 0;

        for (int i = 0; i < prices.size(); i++) {
            int price = extractNumber(prices.get(i).getText());
            int quantity = extractNumber(itemQuantities.get(i).getText());

            calculatedTotal += price * quantity;
        }

        return calculatedTotal;
    }

    public int calculateOrderTotalFromDisplayedItemTotals() {
        if (displayedItemTotals.isEmpty()) {
            throw new IllegalStateException("No item totals were displayed.");
        }

        return displayedItemTotals.stream()
                .mapToInt(element -> extractNumber(element.getText()))
                .sum();
    }

    private int extractNumber(String text) {
        String digitsOnly = text.replaceAll("[^0-9]", "");

        if (digitsOnly.isBlank()) {
            throw new IllegalArgumentException(
                    "No numeric value was found in: " + text
            );
        }

        return Integer.parseInt(digitsOnly);
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public void clickViewCartButton() {
        viewCartButton.click();
    }

    public int getQuantity() {
        quantityButton.getText();
        return Integer.parseInt(quantityButton.getText());
    }

    public void clickQuantityButton() {
        quantityButton.click();
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }

    public String confirmationMessage() {
        return confirmationMessage.getText();
    }
    public boolean isCartEmpty() {
        return $(emptyCartMessage).isVisible();
    }
}
