package com.sathish.stepdefs;

import com.sathish.base.Base;
import com.sathish.dataproviders.ExcelUtil;
import com.sathish.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.Assert;

public class LoginSteps extends Base {
    LoginPage loginPage;
    
    @DataProvider(name = "excelData")
    public Object[][] getExcelData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/TestData/TestData.xlsx";
        String sheetName = "Sheet1";
        return ExcelUtil.readExcelData(filePath, sheetName);
    }
    @Given("I open the login page")
    public void iOpenTheLoginPage() throws IOException {
        initializeDriver("chrome");
        Base.getDriver().get("http://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(Base.getDriver());
        System.out.println("Thread " + Thread.currentThread().getId());
        String filePath = System.getProperty("user.dir") + "/src/test/resources/TestData/TestData.xlsx";
        String sheetName = "Sheet1";
        Object[][] data = ExcelUtil.readExcelData(filePath, sheetName);
        for (Object[] row : data) {
            for (Object cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println(); // New line after each row
        }

    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Then("I should see the secure area")
    public void iShouldSeeTheSecureArea() {
        Assert.assertTrue(loginPage.getSuccessMessage().contains("You logged into a secure area!"));
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        Assert.assertTrue(loginPage.getErrorMessage().contains("Your username is invalid!"));
    }

    // Cucumber scenario outline with Examples
    @Given("I perform login with valid credentials")
    public void performLoginWithValidCredentials() throws IOException {
        for (Object[] row : getExcelData()) {
            String username = (String) row[0];
            String password = (String) row[1];

            // Perform login and verify based on credentials
            iOpenTheLoginPage();
            iEnterUsernameAndPassword(username, password);
            if (username.equals("tomsmith") && password.equals("SuperSecretPassword!")) {
                iShouldSeeTheSecureArea();
            } else {
                iShouldSeeAnErrorMessage();
            }
        }
    }
}