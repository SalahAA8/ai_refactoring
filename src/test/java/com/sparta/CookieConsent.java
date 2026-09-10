package com.sparta;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CookieConsent {
    public static void automationExercisePopup(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Use findElements to avoid exceptions if the button is not present
        List<WebElement> consentButtons = driver.findElements(By.cssSelector("button.fc-cta-consent"));

        if (!consentButtons.isEmpty() && consentButtons.getFirst().isDisplayed()) {
            consentButtons.getFirst().click();
            System.out.println("Consent popup appeared – clicked Accept.");

            // Wait until the popup disappears
            wait.until(ExpectedConditions.invisibilityOf(consentButtons.getFirst()));
        } else {
            System.out.println("Consent popup did not appear.");
        }
    }
}
