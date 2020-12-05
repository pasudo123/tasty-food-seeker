package org.pasudo123.tastyfoodseeker.crawl.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.pasudo123.tastyfoodseeker.crawl.constants.TastyFoodSeekXPath;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageLocation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class TastyFoodSeekExplorer {
    private static final String CRAWL_URL = "http://opengov.seoul.go.kr/expense/seoul";
    private static final int INIT_PAGE = 1;

    private final AtomicInteger currentPage = new AtomicInteger(INIT_PAGE);
    private final WebDriver webDriver;
    private final TastyFoodSeekCrawler crawler;

    public void doExploring(final int year, final int month) {
        this.openBrowser();
        this.selectYearAndMonth(year, month);
        this.searchBtnClick();

        // 다음 페이지로 이동여부 확인
        while(this.isNextPage()) {
            log.info("current viewing page : {}", webDriver.getCurrentUrl());
            final String currentViewingPageUrl = webDriver.getCurrentUrl();
            final List<UsageLocation> usageLocations = crawler.doCrawl(currentViewingPageUrl);

            // TODO save 수행할수 있도록 한다.
        }

        this.closeBrowser();
    }

    private void openBrowser() {
        webDriver.get(CRAWL_URL);
    }

    private void closeBrowser() {
        webDriver.close();
    }

    private void switchByUrl(final String url) {
//        webDriver.get(url);
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
