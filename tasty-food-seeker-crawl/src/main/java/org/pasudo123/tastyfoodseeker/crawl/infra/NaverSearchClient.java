package org.pasudo123.tastyfoodseeker.crawl.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverSearchClient {

    @Value("${naver.search-openapi.client-id}") private String clientId;
    @Value("${naver.search-openapi.secret-key}") private String secretKey;

    private static final String NAVER_CLIENT_ID_HEADER = "X-Naver-Client-Id";
    private static final String NAVER_CLIENT_SECRET_HEADER = "X-Naver-Client-Secret";
    private static final String BASE_API = "https://openapi.naver.com/v1/search/local.json";

    private final WebClient client;

    public void getLocationInfoByApi(final String query) {
        final String uri = BASE_API.concat("?display=5&query=").concat(query);
        final String response = client.method(HttpMethod.GET)
                .uri(uri)
                .header(NAVER_CLIENT_ID_HEADER, clientId)
                .header(NAVER_CLIENT_SECRET_HEADER, secretKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("response : {}", response);
    }
}
