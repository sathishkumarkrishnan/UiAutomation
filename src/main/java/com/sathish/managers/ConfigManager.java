package com.sathish.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private Properties properties = new Properties();

    public ConfigManager() {
        try (FileInputStream file = new FileInputStream("src/test/resources/config/config.properties")) {
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load configuration file.");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
