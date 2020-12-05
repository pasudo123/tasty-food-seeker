package org.pasudo123.tastyfoodseeker.crawl.component;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TastyFoodSeekCrawler {

    private static final String CRAWL_URL = "http://opengov.seoul.go.kr/expense/seoul";
    private final WebDriver webDriver;

    public void doCrawling() {
        this.openBrowser();

        this.closeBrowser();
    }

    private void openBrowser() {
        webDriver.get(CRAWL_URL);
    }

    private void closeBrowser() {
        webDriver.close();
    }
}
