package com.sathish.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class SeleniumExceptionHandler {

    private WebDriver driver;
    private WebDriverWait wait;

    public SeleniumExceptionHandler(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Handle common Selenium exceptions
     * @param exception The exception to handle
     */
//    public void handleException(Exception exception, WebElement element) {
//     
//    }

    /**
     * Take a screenshot and save it to the specified file path
     * @param fileName The file name for the screenshot
     */
    public void takeScreenshot(String fileName) {
        try {
            if (driver instanceof TakesScreenshot) {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotPath = "screenshots/" + fileName;
                Files.createDirectories(Paths.get("screenshots"));
                Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
                System.out.println("Screenshot saved at: " + screenshotPath);
            } else {
                System.err.println("Driver does not support screenshots.");
            }
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error while taking screenshot: " + e.getMessage());
        }
    }

	public void handleException(Exception exception, WebElement element) {
		// TODO Auto-generated method stub
		   if (exception instanceof NoSuchElementException) {
	            System.err.println("Element not found: " + exception.getMessage());
	        } else if (exception instanceof TimeoutException) {
	            System.err.println("Operation timed out: " + exception.getMessage());
	        } else if (exception instanceof StaleElementReferenceException) {
	            System.err.println("Stale element reference: " + exception.getMessage());
	            int retryCount = 3;
	            while (retryCount > 0) {
	                try {
	                    // Retry logic
	                    return;
	                } catch (StaleElementReferenceException e) {
	                    retryCount--;
	                    if (retryCount == 0) {
	                        throw e; // Rethrow after retries are exhausted
	                    }
	                }
	            }


	           
	        } else if (exception instanceof ElementNotInteractableException) {
	            System.err.println("Element not interactable: " + exception.getMessage());
	        } else if (exception instanceof ElementClickInterceptedException) {
	            System.err.println("Element click intercepted: " + exception.getMessage());
	        } else if (exception instanceof NoSuchWindowException) {
	            System.err.println("No such window: " + exception.getMessage());
	        } else if (exception instanceof NoSuchFrameException) {
	            System.err.println("No such frame: " + exception.getMessage());
	        } else if (exception instanceof UnhandledAlertException) {
	            System.err.println("Unhandled alert: " + exception.getMessage());
	        } else if (exception instanceof WebDriverException) {
	            System.err.println("WebDriver exception: " + exception.getMessage());
	        } else {
	            System.err.println("Unknown exception: " + exception.getMessage());
	        }

	        // Optionally, take a screenshot for debugging purposes
	        takeScreenshot("Exception_" + System.currentTimeMillis() + ".png");
	}
}
