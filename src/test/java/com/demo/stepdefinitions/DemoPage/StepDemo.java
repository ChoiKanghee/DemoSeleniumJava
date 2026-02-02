package com.demo.stepdefinitions.DemoPage;

import com.demo.pages.DemoPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDemo {
    @Given("I navigate to Guru page")
    public void iNavigateToGuruPage() {
        DemoPage.visit();
    }

    @Then("User should be navigate to Guru page")
    public void userShouldBeNavigateToGuruPage() {
        DemoPage.verifyUrl();
    }

    @When("I leave Email field is blank")
    public void iLeaveEmailFieldIsBlank() {
        DemoPage.leaveBlank();
    }

    @Then("Alert message {string} should be display")
    public void alertMessageShouldBeDisplay(String expectedMessage) {
        DemoPage.verifyErrorMsgPresent(expectedMessage);
    }

    @When("I input {string} email")
    public void iInputEmail(String email) {
        DemoPage.iInputEmail(email);
    }

    @Then("Homepage should be display as design")
    public void homepageShouldBeDisplayAsDesign() {
        DemoPage.verifyHomePage();
    }

    @When("I open Selenium dropdown")
    public void iOpenSeleniumDropdown() {
        DemoPage.openSeleniumDropDownList();
    }

    @Then("I should see these Selenium dropdown items:")
    public void iShouldSeeTheseSeleniumDropdownItems(DataTable dataTable) {
        DemoPage.verifySeleniumDropdownItems(dataTable);
    }

    @When("I click on {string} menu")
    public void iClickOnMenu(String menuName) {
        DemoPage.iClickOnMenu(menuName);
    }

    @Then("I should be navigated to {string} page")
    public void iShouldBeNavigatedToPage(String expectedPageURL) {
        DemoPage.verifyNavigateURLPage(expectedPageURL);
    }

    @When("i open new tab")
    public void iOpenNewTab() {
        DemoPage.openNewTab();
    }

    @When("I input masked email into Email field")
    public void iInputMaskedEmailIntoEmailField() {
        DemoPage.inputMaskedEmail();
    }
}
