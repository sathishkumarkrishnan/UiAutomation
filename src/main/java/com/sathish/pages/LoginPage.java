package com.sathish.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.sathish.utils.SeleniumExceptionHandler;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private SeleniumExceptionHandler exceptionHandler;

    // Locators
    private By usernameFieldLocator = By.id("username");
    private By passwordFieldLocator = By.id("password");
    private By loginButtonLocator = By.cssSelector("button[type='submit']");
    private By successMessageLocator = By.cssSelector(".flash.success");
    private By errorMessageLocator = By.cssSelector(".flash.error");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Initialize WebDriverWait
        this.exceptionHandler = new SeleniumExceptionHandler(driver); // Initialize SeleniumExceptionHandler
    }

    // Actions
    public void enterUsername(String username) {
        try {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFieldLocator));
            usernameField.clear();
            usernameField.sendKeys(username);
        } catch (Exception e) {
            exceptionHandler.handleException(e, driver.findElement(usernameFieldLocator));
        }
    }

    public void enterPassword(String password) {
        try {
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFieldLocator));
            passwordField.clear();
            passwordField.sendKeys(password);
        } catch (Exception e) {
            exceptionHandler.handleException(e, driver.findElement(passwordFieldLocator));
        }
    }

    public void clickLoginButton() {
        try {
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
            loginButton.click();
        } catch (Exception e) {
            exceptionHandler.handleException(e, driver.findElement(loginButtonLocator));
        }
    }

    public String getSuccessMessage() {
        try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));
            return successMessage.getText();
        } catch (Exception e) {
            exceptionHandler.handleException(e, driver.findElement(successMessageLocator));
            return null;
        }
    }

    public String getErrorMessage() {
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
            return errorMessage.getText();
        } catch (Exception e) {
            exceptionHandler.handleException(e, driver.findElement(errorMessageLocator));
            return null;
        }
    }
}
