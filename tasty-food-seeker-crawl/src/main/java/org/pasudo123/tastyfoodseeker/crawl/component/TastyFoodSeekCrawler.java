package org.pasudo123.tastyfoodseeker.crawl.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.pasudo123.tastyfoodseeker.crawl.constants.TastyFoodSeekXPath;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageDetailPage;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageLocation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TastyFoodSeekCrawler {

    private final WebDriver webDriver;

    public List<UsageLocation> doCrawl(final String currentViewingPageUrl) {
        webDriver.get(currentViewingPageUrl);

        final List<UsageLocation> usageLocations = new ArrayList<>();

        List<WebElement> tableRowTitleElements = findTableRowElementsByElement(findTableElement());

        /** 특정한 페이지내 각 기관별 목록 **/
        for(int rowIndex = 0; rowIndex < tableRowTitleElements.size(); rowIndex++) {
            final WebElement titleElement = tableRowTitleElements.get(rowIndex);
            titleElement.click();

            /** 특정 기관의 업무추진비 사용내역 페이지 **/
            UsageDetailPage.of(webDriver.findElements(TastyFoodSeekXPath.Path.getListUsageTablePath()))
                    .ifPresent(usageDetailPage -> {
                        usageLocations.addAll(usageDetailPage.getUsageLocations());
                    });

            pageBack();

            // webDriver 는 navigate 시, 이전요소들은 만료되기때문에 다시 갱신이 필요하다.
            tableRowTitleElements = findTableRowElementsByElement(findTableElement());
        }

        return usageLocations;
    }

    private void pageBack() {
        webDriver.navigate().back();
    }

    /**
     * 테이블 엘리먼트 영역 획득
     */
    private WebElement findTableElement() {
        return webDriver.findElement(TastyFoodSeekXPath.Path.getListTablePath());
    }

    /**
     * 특정한 테이블 엘리먼트로부터 타이틀 엘리먼트 목록 획득
     */
    private List<WebElement> findTableRowElementsByElement(final WebElement element) {
        return element.findElements(TastyFoodSeekXPath.Path.getListTableRowAPath());
    }
}
