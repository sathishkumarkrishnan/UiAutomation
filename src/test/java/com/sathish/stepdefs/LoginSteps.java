package com.sathish.stepdefs;

import com.sathish.base.Base;
import com.sathish.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginSteps extends Base {
    LoginPage loginPage;

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
        initializeDriver("chrome");
        driver.get("http://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @When("I enter the valid username and password")
    public void iEnterValidCredentials() {
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();
    }

    @Then("I should see the secure area")
    public void iShouldSeeTheSecureArea() {
        String successMessage = loginPage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("You logged into a secure area!"),
                "Expected success message not found!");
        tearDown();
    }

    @When("I enter an invalid username and password")
    public void iEnterInvalidCredentials() {
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("invalidPassword");
        loginPage.clickLoginButton();
    }

    @Then("I should see an error message")
    public void iShouldSeeErrorMessage() {
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Your username is invalid!"),
                "Expected error message not found!");
        tearDown();
    }
}
