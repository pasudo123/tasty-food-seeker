package org.pasudo123.tastyfoodseeker.crawl.pojo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.pasudo123.tastyfoodseeker.crawl.constants.TastyFoodSeekXPath;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 업무추진비 사용내역 페이지
 */
public class UsageDetailPage {

    private static final String TD_TAG = "td";
    private final WebElement element;

    private UsageDetailPage(final WebElement element) {
        this.element = element;
    }

    public static Optional<UsageDetailPage> of(List<WebElement> elements) {
        if(elements.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(new UsageDetailPage(elements.get(0)));
    }

    /**
     * 사용목록에서 사용장소 및 시간을 획득해서 UsageLocation 으로 변환 및 Collection list 로 반환한다.
     */
    public List<UsageLocation> getUsageLocations() {
        return element.findElements(TastyFoodSeekXPath.Path.getListUsageTableRowPath())
                .stream()
                .map(this::getUsageLocation)
                .filter(UsageLocation::isExistLocation)
                .collect(Collectors.toList());
    }

    private UsageLocation getUsageLocation(final WebElement element) {
        // 0 : 연번
        // 1 : 부서명
        // 2 : 사용일시
        // 3 : 사용장소
        // 4 : 사용목적
        // 5 : 사용금액
        // 6 : 사용자 및 인원
        final LocalDate usageDate = LocalDate.parse(element.findElements(By.tagName(TD_TAG)).get(2).getText().split("\\s")[0]);
        final String locationName = element.findElements(By.tagName(TD_TAG)).get(3).getText();
        return new UsageLocation(usageDate, locationName);
    }
}
