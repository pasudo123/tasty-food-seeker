package org.pasudo123.tastyfoodseeker.crawl.component;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.pasudo123.tastyfoodseeker.crawl.constants.TastyFoodSeekXPath;
import org.pasudo123.tastyfoodseeker.crawl.noti.CrawlEventCode;
import org.pasudo123.tastyfoodseeker.crawl.noti.NotificationService;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageDetailPage;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageLocation;
import org.pasudo123.tastyfoodseeker.crawl.service.CrawlDataPipelineService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TastyFoodSeekCrawler {

    private final Set<String> URL_LOCAL_STORE = new HashSet<>();
    private final TastyFoodSeekerValidator validator;
    private final CrawlDataPipelineService crawlDataPipelineService;
    private final NotificationService notificationService;
    private WebDriver webDriver;

    public void doCrawl(final WebDriver webDriver, final String currentViewingPageUrl) {
        this.checkParams(webDriver, currentViewingPageUrl);
        this.webDriver = webDriver;
        webDriver.get(currentViewingPageUrl);

        final List<UsageLocation> usageLocations = new ArrayList<>();

        List<WebElement> tableRowTitleElements = findTableRowElementsByElement(findTableElement());

        /** 특정한 페이지내 각 기관별 목록 **/
        for(int rowIndex = 0; rowIndex < tableRowTitleElements.size(); rowIndex++) {
            final WebElement titleElement = tableRowTitleElements.get(rowIndex);
            titleElement.click();

            /** 해당 URL 을 이전에 탐색했는지 여부 확인 **/
            if(this.checkDuplicateUrl(webDriver.getCurrentUrl())){
                return;
            }

            /** 특정 기관의 업무추진비 사용내역 페이지 **/
            UsageDetailPage.of(webDriver.findElements(TastyFoodSeekXPath.Path.getListUsageTablePath()))
                    .ifPresent(usageDetailPage -> {
                        usageLocations.addAll(usageDetailPage.getUsageLocations());
                    });

            // 크롤링한 데이터를 기반으로 client 에게 요청보내도록 한다.
            crawlDataPipelineService.execute(usageLocations);
            usageLocations.clear();

            // webDriver 는 navigate 시, 이전요소들은 만료되기때문에 다시 갱신이 필요하다.
            historyBack();
            tableRowTitleElements = findTableRowElementsByElement(findTableElement());
        }

        usageLocations.clear();
    }

    /**
     * url 을 중복으로 탐색했는지 여부 확인
     */
    private boolean checkDuplicateUrl(final String currentUrl) {
        if(URL_LOCAL_STORE.contains(currentUrl)) {
            notificationService.toPost(
                    String.format("해당 %s 는 이전에 한번 탐색되었던 주소입니다.\n다음 페이지로 넘어갑니다.", webDriver.getCurrentUrl()),
                    CrawlEventCode.DUPLICATE_CHILD_URL_ERROR);
            URL_LOCAL_STORE.clear();
            return true;
        }

        URL_LOCAL_STORE.add(webDriver.getCurrentUrl());
        return false;
    }

    /**
     * 유효한 params 여부 확인
     */
    private void checkParams(final WebDriver webDriver, final String currentViewingPageUrl) {
        validator.isValidDriver(webDriver);
        validator.isValidUrl(currentViewingPageUrl);
    }

    /**
     * 현재 페이지에서 뒤로 가기
     */
    private void historyBack() {
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
