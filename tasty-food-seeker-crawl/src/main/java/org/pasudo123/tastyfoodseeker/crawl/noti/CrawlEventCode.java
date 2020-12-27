package org.pasudo123.tastyfoodseeker.crawl.noti;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CrawlEventCode {

    DUPLICATE_PARENT_URL_ERROR(500, "동일 url 에 대해서 크롤링을 시도하고 있습니다."),
    DUPLICATE_CHILD_URL_ERROR(500, "동일 url 에 대해서 크롤링을 시도하고 있습니다.")
    ;

    private final int status;
    private final String detail;
}
