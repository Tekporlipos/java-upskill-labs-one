package com.amalitech.upskilling.week_one.lab_two.creational.singleton;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

class ConfigurationManager {
    private static ConfigurationManager instance;
    private final Properties properties;

    private ConfigurationManager() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

class SingletonConfigExample {
    public static void main(String[] args) {
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        String dbUrl = configManager.getProperty("db.url");
        System.out.println("Database URL: " + dbUrl);
    }
}

