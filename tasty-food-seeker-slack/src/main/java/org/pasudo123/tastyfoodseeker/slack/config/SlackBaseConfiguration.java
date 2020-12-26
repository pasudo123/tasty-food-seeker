package org.pasudo123.tastyfoodseeker.slack.config;

import com.hubspot.slack.client.SlackClient;
import com.hubspot.slack.client.SlackClientFactory;
import com.hubspot.slack.client.SlackClientRuntimeConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackBaseConfiguration {

    @Value("${slack.token:override}")
    public String token;

    @Bean
    public SlackClient slackClient() {
        return SlackClientFactory.defaultFactory().build(config());
    }

    private SlackClientRuntimeConfig config() {
        return SlackClientRuntimeConfig.builder()
                .setTokenSupplier(() -> token)
                .build();
    }
}
