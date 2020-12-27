package org.pasudo123.tastyfoodseeker.crawl.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebDriverGenerator {

    private static final String DEV_ENV = "dev";
    private final Environment environment;

    @Value("${web-driver.path:http://selenium_hub:4444/wd/hub}")
    private String webDriverPath;

    public Optional<WebDriver> generate() {
        // dev
        if (isDevEnv()) {
            System.setProperty("webdriver.chrome.driver", webDriverPath);
            final WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            return Optional.of(driver);
        }

        // prod + etc
        try {
            final ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--headless");
            final URL url = new URL(webDriverPath);
            final WebDriver driver = new RemoteWebDriver(url, opt);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            return Optional.of(driver);
        } catch (MalformedURLException e) {
            log.error("web driver initialize exception : {}", e.getMessage());
            return Optional.empty();
        }
    }

    private boolean isDevEnv() {
        final String[] profiles = environment.getActiveProfiles();
        return Arrays.stream(profiles)
                .anyMatch(env -> env.equalsIgnoreCase(DEV_ENV));
    }
}
