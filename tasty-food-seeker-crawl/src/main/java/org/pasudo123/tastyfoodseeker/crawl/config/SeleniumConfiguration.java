package org.pasudo123.tastyfoodseeker.crawl.config;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class SeleniumConfiguration {

    @Value("${web-driver.host-url:http://localhost:14444/wd/hub}")
    private String hostUrl;

    @Profile("dev")
    @Bean
    public WebDriver devWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\develop\\workspace\\tasty-food-seeker\\tasty-food-seeker-crawl\\src\\main\\resources\\chromedriver.exe");
        final WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver;
    }

    @Profile("prod")
    @Bean
    public WebDriver prodWebDriver() {
        try {
            // headless 설정 및 selenium container 이용
            final ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--headless");
            
            final WebDriver driver = new RemoteWebDriver(new URL(hostUrl), opt);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            return driver;
        } catch (MalformedURLException e) {
            log.error("web driver initialize exception : {}", e.getMessage());
            return null;
        }
    }
}
