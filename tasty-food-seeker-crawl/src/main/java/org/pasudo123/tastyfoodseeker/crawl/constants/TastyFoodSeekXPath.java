package org.pasudo123.tastyfoodseeker.crawl.constants;

import org.openqa.selenium.By;

/**
 * 크롤을 하기위한 XPath 정보
 */
public class TastyFoodSeekXPath {

    private TastyFoodSeekXPath() {}

    /**
     * 서울시 업무추진비 화면에 대한 컴포넌트 위치값
     */
    public static class Path {
        // 연월
        private static final String YEAR_SELECTOR_PATH = "/html/body/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/div[2]/form/div/div[1]/select[1]";
        private static final String MONTH_SELECTOR_PATH = "/html/body/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/div[2]/form/div/div[1]/select[2]";

        // 검색버튼
        private static final String SEARCH_BUTTON_PATH = "/html/body/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/div[2]/form/div/input";

        // 기관별 업무추진비 테이블 및 데이터 로우
        private static final String LIST_TABLE_PATH = "/html/body/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/div[3]/table";
        private static final String LIST_TABLE_ROW_A_PATH = "/html/body/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/div[3]/table/tbody/tr/td/p/a";

        // 기관별 사용내역 테이블 및 데이터 로우
        private static final String LIST_USAGE_TABLE_PATH = "/html/body/div[2]/div/div[2]/div[2]/div/div/div[1]/div[2]/div[2]/table";
        private static final String LIST_USAGE_TABLE_ROW_PATH = "/html/body/div[2]/div/div[2]/div[2]/div/div/div[1]/div[2]/div[2]/table/tbody/tr";

        // 페이지 네비게이션
        private static final String PAGER_PATH = "/html/body/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/div[3]/div[2]/ul";
        private static final String PAGER_LI_TAG = "li";

        public static By getYearSelectorPath() {
            return By.xpath(YEAR_SELECTOR_PATH);
        }
        public static By getMonthSelectorPath() { return By.xpath(MONTH_SELECTOR_PATH); }
        public static By getSearchButtonPath() { return By.xpath(SEARCH_BUTTON_PATH); }
        public static By getListTablePath() { return By.xpath(LIST_TABLE_PATH); }
        public static By getListTableRowAPath() { return By.xpath(LIST_TABLE_ROW_A_PATH); }
        public static By getListUsageTablePath() { return By.xpath(LIST_USAGE_TABLE_PATH); }
        public static By getListUsageTableRowPath() { return By.xpath(LIST_USAGE_TABLE_ROW_PATH); }
        public static By getPagerPath() { return By.xpath(PAGER_PATH); }
        public static By getPagerLiTag() { return By.tagName(PAGER_LI_TAG); }
    }
}
