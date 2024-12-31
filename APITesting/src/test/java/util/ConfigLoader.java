package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration properties!", e);
        }
    }

    public static String getProperty(String key) {
        // Return null if property is missing. This is to handle the nullpointer exception
        return properties.getProperty(key);
    }
}