package org.pasudo123.tastyfoodseeker.web.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.web.infra.pojo.geo.NaverGeoItems;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverGeoCodingClient {

    @Value("${naver.map-openapi.client-id}") private String clientId;
    @Value("${naver.map-openapi.secret-key}") private String secretKey;

    private static final String NAVER_GC_API_KEY_ID_HEADER = "X-NCP-APIGW-API-KEY-ID";
    private static final String NAVER_GC_API_KEY_HEADER = "X-NCP-APIGW-API-KEY";
    private static final String BASE_API = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode";
    private final WebClient client;

    public Optional<NaverGeoItems> getGeoByApi(final String query) {
        if(query.isEmpty()) {
            return Optional.empty();
        }

        final String uri = BASE_API.concat("?query=").concat(query);

        final NaverGeoItems response = client.method(HttpMethod.GET)
                .uri(uri)
                .header(NAVER_GC_API_KEY_ID_HEADER, clientId)
                .header(NAVER_GC_API_KEY_HEADER, secretKey)
                .retrieve()
                .bodyToMono(NaverGeoItems.class)
                .block();

        return Optional.ofNullable(response);
    }

}
