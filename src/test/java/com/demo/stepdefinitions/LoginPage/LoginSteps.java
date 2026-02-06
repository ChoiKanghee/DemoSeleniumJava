package com.demo.stepdefinitions.LoginPage;

import com.demo.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginSteps {
    @Given("user navigate to Google page")
    public void userNavigateToGooglePage() {
        LoginPage.visit();
    }

    @Then("google page should be display")
    public void googlePageShouldBeDisplay() {
        LoginPage.verifyPage();
    }

    @And("i input {string} text to search box")
    public void iInputTextToSearchBox(String text) {
        LoginPage.inputText(text);
    }

    @And("i click on submit button")
    public void iClickOnSubmitButton() {
        LoginPage.clickSubmitBtn();
    }
}
