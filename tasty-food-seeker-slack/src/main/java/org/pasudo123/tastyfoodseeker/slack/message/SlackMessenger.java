package org.pasudo123.tastyfoodseeker.slack.message;

import com.hubspot.algebra.Result;
import com.hubspot.slack.client.SlackClient;
import com.hubspot.slack.client.methods.params.chat.ChatPostMessageParams;
import com.hubspot.slack.client.models.response.SlackError;
import com.hubspot.slack.client.models.response.chat.ChatPostMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SlackMessenger {

    @Value("${slack.channelId}")
    private String channelId;
    private final SlackClient client;

    public void postMessage(final String message) {
        final Result<ChatPostMessageResponse, SlackError> result = client.postMessage(
                ChatPostMessageParams.builder()
                        .setText(message)
                        .setChannelId(channelId)
                        .build()
        ).join();
    }
}
