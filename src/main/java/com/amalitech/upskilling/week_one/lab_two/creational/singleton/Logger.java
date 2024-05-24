package com.amalitech.upskilling.week_one.lab_two.creational;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Logger {
    private static Logger instance;

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println(message);
    }
}

class SingletonLoggerExample {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("This is a log message.");
    }
}


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