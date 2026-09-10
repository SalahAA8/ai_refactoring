package com.sparta.steps;

import com.sparta.CookieConsent;
import com.sparta.pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class ProductSteps {
    @Managed
    private ProductPage productPage;

    @Given("I am on the page for product {int}")
    public void iAmOnThePageForProduct(int id) {
        productPage.open(id);
        CookieConsent.automationExercisePopup(productPage.getDriver());
    }

    @Then("I should be able to see the average score of the product")
    public void iShouldBeAbleToSeeTheAverageScoreOfTheProduct() {
        assertThat(productPage.getRating().isVisible(), is(true));
    }

    @When("I enter {string} into the review name field")
    public void iEnterIntoTheReviewNameField(String name) {
        productPage.typeReviewName(name);
    }

    @And("I enter {string} into review email field")
    public void iEnterIntoReviewEmailField(String email) {
        productPage.typeReviewEmail(email);
    }

    @And("I enter {string} into the review text field")
    public void iEnterIntoTheReviewTextField(String review) {
        productPage.typeReviewContent(review);
    }

    @And("I press submit")
    public void iPressSubmit() {
        productPage.submitReview();
    }

    @Then("the message {string} should appear")
    public void theMessageShouldAppear(String message) {
        assertThat(productPage.getReviewSubmitMessage(), is(message));
    }

    @Then("I should be able to see the availability of the product")
    public void iShouldBeAbleToSeeTheAvailabilityOfTheProduct() {
        assertThat(productPage.getAvailability(), startsWith("Availability: "));
    }

    @And("I should be able to see the condition of the product")
    public void iShouldBeAbleToSeeTheConditionOfTheProduct() {
        assertThat(productPage.getCondition(), startsWith("Condition: "));
    }

    @And("I should be able to see the brand of the product")
    public void iShouldBeAbleToSeeTheBrandOfTheProduct() {
        assertThat(productPage.getBrand(), startsWith("Brand: "));
    }

    @Then("I should be able to see the category of the product")
    public void iShouldBeAbleToSeeTheCategoryOfTheProduct() {
        assertThat(productPage.getCategory(), startsWith("Category: "));
        assertThat(productPage.getCategory(), containsString(">"));
    }
}
