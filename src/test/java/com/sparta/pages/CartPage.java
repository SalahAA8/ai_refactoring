package com.sparta.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {
    private final Page page;

    private final Locator productsButton;
    private final Locator addFirstProductButton;
    private final Locator continueShoppingButton;
    private final Locator cartButton;
    private final Locator proceedToCheckoutButton;
    private final Locator cartTable;
    private final Locator placeOrderButton;
    private final Locator deliveryAddress;
    private final Locator billingAddress;
    private final Locator prices;
    private final Locator itemPrices;
    private final Locator itemQuantities;
    private final Locator displayedItemTotals;
    private final Locator totalAmount;
    private final Locator addToCartButton;
    private final Locator viewCartButton;
    private final Locator quantityButton;
    private final Locator checkOutButton;
    private final Locator totalPrice;
    private final Locator deleteButton;
    private final Locator confirmationMessage;
    private final Locator emptyCartMessage;
    private final Locator cartRows;
    private final Locator addSecondProductButton;

    public CartPage(Page page) {
        this.page = page;

        productsButton = page.locator("xpath=//a[contains(text(), 'Products')]");
        addFirstProductButton = page.locator("xpath=(//a[contains(text(), 'Add to cart')])[1]");
        continueShoppingButton = page.locator("xpath=//button[contains(text(), 'Continue Shopping')]");
        cartButton = page.locator("xpath=//a[contains(text(), 'Cart')]");
        proceedToCheckoutButton = page.locator("xpath=//a[contains(text(), 'Proceed To Checkout')]");
        cartTable = page.locator("xpath=cart_info");
        placeOrderButton = page.locator("xpath=//a[contains(text(), 'Place Order')]");
        deliveryAddress = page.locator("#address_delivery");
        billingAddress = page.locator("#address_invoice");
        prices = page.locator(".cart_total_price");
        itemPrices = page.locator("#cart_info tbody tr:not(:last-child) .cart_price p");
        itemQuantities = page.locator("#cart_info tbody tr:not(:last-child) .cart_quantity button");
        displayedItemTotals = page.locator("#cart_info tbody tr:not(:last-child) .cart_total_price");
        totalAmount = page.locator("#cart_info tbody tr:last-child .cart_total_price");
        addToCartButton = page.locator("a.add-to-cart");
        viewCartButton = page.locator("#cartModal a[href='/view_cart']");
        quantityButton = page.locator("button.disabled");
        checkOutButton = page.locator("button.check_out");
        totalPrice = page.locator("p.cart_total_price");
        deleteButton = page.locator("a.cart_quantity_delete");
        confirmationMessage = page.locator("#cartModal .modal-body p.text-center");
        emptyCartMessage = page.locator("#empty_cart");
        cartRows = page.locator("#cart_info_table tbody tr");
        addSecondProductButton = page.locator("xpath=(//a[contains(text(), 'Add to cart')])[2]");
    }

    public void addProductToCart() {
        productsButton.click();
        addFirstProductButton.click();
        continueShoppingButton.click();
    }

    public void addSecondProductToCart() {
        productsButton.click();
        addSecondProductButton.click();
        continueShoppingButton.click();
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
        return page.url().contains("checkout");
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
        var priceString = prices.last().innerText().substring(4);
        return Integer.parseInt(priceString);
    }

    public int getSumPrice() {
        int sum = 0;

        for (int i = prices.count() - 2; i >= 0; i--) {
            sum += Integer.parseInt(prices.nth(i).innerText().substring(4));
        }

        return sum;
    }

    public boolean isTotalAmountDisplayed() {
        return totalAmount.isVisible();
    }

    public int getDisplayedOrderTotal() {
        return extractNumber(totalAmount.innerText());
    }

    public int calculateOrderTotalFromPriceAndQuantity() {
        int priceCount = prices.count();

        if (priceCount == 0 || priceCount != itemQuantities.count()) {
            throw new IllegalStateException(
                    "Could not calculate total because the item prices and quantities did not match."
            );
        }

        int calculatedTotal = 0;

        for (int i = 0; i < priceCount; i++) {
            int price = extractNumber(prices.nth(i).innerText());
            int quantity = extractNumber(itemQuantities.nth(i).innerText());

            calculatedTotal += price * quantity;
        }

        return calculatedTotal;
    }

    public int calculateOrderTotalFromDisplayedItemTotals() {
        int displayedItemTotalCount = displayedItemTotals.count();

        if (displayedItemTotalCount == 0) {
            throw new IllegalStateException("No item totals were displayed.");
        }

        int total = 0;

        for (int i = 0; i < displayedItemTotalCount; i++) {
            total += extractNumber(displayedItemTotals.nth(i).innerText());
        }

        return total;
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
        quantityButton.innerText();
        return Integer.parseInt(quantityButton.innerText());
    }

    public void clickQuantityButton() {
        quantityButton.click();
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }

    public String confirmationMessage() {
        return confirmationMessage.innerText();
    }

    public boolean isCartEmpty() {
        return emptyCartMessage.isVisible();
    }
}

