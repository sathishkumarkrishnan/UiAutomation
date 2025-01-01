package com.sathish.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.SocketException;
import java.time.Duration;

public class Base {
    protected static WebDriverWait wait;

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initializeDriver(String browser) throws SocketException {
        WebDriver localDriver;
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            localDriver = new org.openqa.selenium.chrome.ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            localDriver = new org.openqa.selenium.firefox.FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        localDriver.manage().window().maximize();
        driver.set(localDriver);
        wait = new WebDriverWait(localDriver, Duration.ofSeconds(20));
    }

    public static void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
