package org.pasudo123.tastyfoodseeker.crawl.noti;

import lombok.RequiredArgsConstructor;
import org.pasudo123.tastyfoodseeker.slack.message.SlackMessenger;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationHandler {

    private final SlackMessenger slackMessenger;

    @Async
    @EventListener
    public void notify(CrawlEvent event){
        slackMessenger.postMessage(event.getMessage());
    }
}
