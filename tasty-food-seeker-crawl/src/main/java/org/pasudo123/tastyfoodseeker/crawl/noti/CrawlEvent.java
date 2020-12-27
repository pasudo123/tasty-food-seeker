package org.pasudo123.tastyfoodseeker.crawl.noti;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CrawlEvent {
    private final String message;
    private final CrawlEventCode eventCode;

    public String getMessage() {
        return eventCode.name()
                .concat("\n")
                .concat(message);
    }
}
