package org.pasudo123.tastyfoodseeker.crawl.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.pasudo123.tastyfoodseeker.crawl.constants.TastyFoodSeekXPath;
import org.pasudo123.tastyfoodseeker.crawl.exception.WebDriverInitializeException;
import org.pasudo123.tastyfoodseeker.crawl.exception.code.ErrorCode;
import org.pasudo123.tastyfoodseeker.util.WebDriverGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class TastyFoodSeekExplorer {

    private static final String CRAWL_URL = "http://opengov.seoul.go.kr/expense/seoul";
    private static final int INIT_PAGE = 1;
    private final AtomicInteger currentPage = new AtomicInteger(INIT_PAGE);
    private final WebDriverGenerator generator;
    private final TastyFoodSeekerValidator validator;
    private final TastyFoodSeekCrawler crawler;

    private WebDriver webDriver;

    @PostConstruct
    public void init() {
        this.webDriver = generator.generate()
                .orElseThrow(() -> new WebDriverInitializeException(ErrorCode.WEB_DRIVER_INIT_EXCEPTION));
    }

    public void doExploring(final int year, final int month) {
        this.checkParams(year, month);
        this.openBrowser();
        this.selectYearAndMonth(year, month);
        this.searchBtnClick();

        // 다음 페이지로 이동여부 확인
        while(this.isNextPage()) {
            log.info("current viewing page : {}", webDriver.getCurrentUrl());
            final String currentViewingPageUrl = webDriver.getCurrentUrl();
            crawler.doCrawl(webDriver, currentViewingPageUrl);
        }

        this.closeAndQuitBrowser();
    }

    private void checkParams(final int year, final int month) {
        validator.isValidYearAndMonth(year, month);
    }

    private void openBrowser() {
        webDriver.get(CRAWL_URL);
    }

    public void closeAndQuitBrowser() {
        webDriver.close();
        webDriver.quit();
    }

    /**
     * select box 에서 날짜를 선택한다.
     */
    private void selectYearAndMonth(final int year, final int month) {
        final Select currentYearSelect = new Select(webDriver.findElement(TastyFoodSeekXPath.Path.getYearSelectorPath()));
        final Select currentMonthSelect = new Select(webDriver.findElement(TastyFoodSeekXPath.Path.getMonthSelectorPath()));

        currentYearSelect.selectByValue(String.valueOf(year));
        currentMonthSelect.selectByValue(String.valueOf(month));
    }

    /**
     * 검색 버튼을 클릭한다.
     */
    private void searchBtnClick() {
        webDriver.findElement(TastyFoodSeekXPath.Path.getSearchButtonPath())
                .click();
    }

    /**
     * 다음페이지로 이동할 수 있는지 여부
     */
    private boolean isNextPage() {

        if(currentPage.getAndIncrement() == INIT_PAGE) {
            return true;
        }

        // pager 영역에 li 태그를 검색한다.
        final WebElement pagerElement = webDriver.findElement(TastyFoodSeekXPath.Path.getPagerPath());
        final List<WebElement> liTags = pagerElement.findElements(TastyFoodSeekXPath.Path.getPagerLiTag());

        int nextPageIndex = -1;

        for(int currentPageIndex = 0; currentPageIndex < liTags.size(); currentPageIndex++) {
            final WebElement liElement = liTags.get(currentPageIndex);
            final String className = liElement.getAttribute("class");

            if(className.contains("pager-current")) {
                nextPageIndex = currentPageIndex + 1;
                break;
            }
        }

        // 마지막 페이지의 경우
        if(nextPageIndex == -1 || nextPageIndex >= liTags.size()) {
            return false;
        }

        // 다음 페이지 이동
        liTags.get(nextPageIndex).click();
        return true;
    }
}
