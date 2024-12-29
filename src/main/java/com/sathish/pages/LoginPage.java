package com.sathish.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.sathish.utils.SeleniumExceptionHandler;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private SeleniumExceptionHandler exceptionHandler;

    @FindBy(css = ".flash.success")
    private WebElement successMessage;

    @FindBy(css = ".flash.error")
    private WebElement errorMessage;

    // Locators
    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Initialize WebDriverWait
        this.exceptionHandler = new SeleniumExceptionHandler(driver); // Initialize SeleniumExceptionHandler
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void enterUsername(String username) {
        try {
            usernameField.clear();
            usernameField.sendKeys(username);
        } catch (Exception e) {
            exceptionHandler.handleExectpion(e, usernameField);
        }
    }

    public void enterPassword(String password) {
        try {
            passwordField.clear();
            passwordField.sendKeys(password);
        } catch (Exception e) {
            exceptionHandler.handleExectpion(e, passwordField);
        }
    }

    public void clickLoginButton() {
        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        	element.click();

        } catch (Exception e) {
            exceptionHandler.handleExectpion(e, loginButton);
        }
    }

    public String getSuccessMessage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement element = wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return element.getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
