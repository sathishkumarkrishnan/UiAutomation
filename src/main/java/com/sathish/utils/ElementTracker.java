package com.sathish.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementTracker {

    private WebDriver driver;
    private WebElement lastTriedElement; // Variable to hold the last tried WebElement

    public ElementTracker(WebDriver driver) {
        this.driver = driver;
    }

    // Method to find the element and store it as last tried element
//    public WebElement findElement(By locator) {
//        try {
//        	
//            lastTriedElement = driver.findElement(locator); // Store the last tried element
//            return lastTriedElement;
//        } catch (Exception e) {
//            System.err.println("Error finding element: " + e.getMessage());
//            return null;
//        }
//    }
    
//    public void setTriedElement(WebElement element)
//    {
//    	 lastTriedElement=element;
//    }

    // Get the last tried WebElement
    public WebElement getLastTriedElement(WebElement element) {
        if (lastTriedElement != null) {
            return lastTriedElement=element;
        } else {
            throw new IllegalStateException("No WebElement has been tried yet.");
        }
    }
    
    public void click()
    {
    	
    }
}
