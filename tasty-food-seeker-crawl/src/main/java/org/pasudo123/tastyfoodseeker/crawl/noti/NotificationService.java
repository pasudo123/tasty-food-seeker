package org.pasudo123.tastyfoodseeker.crawl.noti;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 개발자 noti 를 위한 서비스 레이어
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ApplicationEventPublisher publisher;

    public void toPost(final String message, final CrawlEventCode eventCode) {
        publisher.publishEvent(new CrawlEvent(message, eventCode));
    }
}
