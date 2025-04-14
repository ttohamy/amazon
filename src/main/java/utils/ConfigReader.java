package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("/Users/mohamedeltohamy/.jenkins/workspace/Run Tests From GitHub/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file. ");
        }
    }

    public static String getProperty(String key) {
        System.out.println("test make sure that the push done");
        return properties.getProperty(key);
    }
}