package com.example.UITesting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {

    @Bean
    public WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hasit\\Downloads\\chromedriver-win64\\chromedriver.exe"); // Add Your chromedriver path
        return new ChromeDriver();
    }
}