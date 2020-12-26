//package org.pasudo123.tastyfoodseeker.crawl.config;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//public class SeleniumConfiguration {
//
//    @Value("${web-driver.path:http://selenium_hub:4444/wd/hub}")
//    private String webDriverPath;
//    private final Environment env;
//
////    @Profile("dev")
////    @Bean
////    public WebDriver devWebDriver() {
////        System.setProperty("webdriver.chrome.driver", webDriverPath);
////        final WebDriver driver = new ChromeDriver();
////        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
////        return driver;
////    }
////
////    @Profile("prod")
////    @Bean
////    public WebDriver prodWebDriver() {
////        try {
////            // headless 설정 및 selenium container 이용
////            final ChromeOptions opt = new ChromeOptions();
////            opt.addArguments("--headless");
////
////            final WebDriver driver = new RemoteWebDriver(new URL(webDriverPath), opt);
////            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
////            return driver;
////        } catch (MalformedURLException e) {
////            log.error("web driver initialize exception : {}", e.getMessage());
////            return null;
////        }
////    }
//
////    public void closeWebDriverByProfile() {
////        final String[] profiles = env.getActiveProfiles();
////        if(Arrays.stream(profiles).anyMatch(env -> env.equalsIgnoreCase("dev"))) {
////            return;
////        }
////
////        final WebDriver webDriver = this.prodWebDriver();
////        webDriver.close();
////        webDriver.quit();
////    }
//}
