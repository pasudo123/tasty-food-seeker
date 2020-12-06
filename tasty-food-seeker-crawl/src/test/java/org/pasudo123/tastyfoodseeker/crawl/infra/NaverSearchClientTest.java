package org.pasudo123.tastyfoodseeker.crawl.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pasudo123.tastyfoodseeker.crawl.config.WebClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("dev")
@SpringBootTest(classes = {
        NaverSearchClient.class
})
@ExtendWith(SpringExtension.class)
@Import(WebClientConfiguration.class)
@DisplayName("네이버 검색 클라이언트는")
class NaverSearchClientTest {

    @Autowired
    private NaverSearchClient naverSearchClient;

    @Test
    @DisplayName("api 를 통해서 위치정보를 획득한다.")
    public void getLocationInfoByApiTest() {
        // given
        final String query = "훈민정";

        naverSearchClient.getLocationInfoByApi(query);
    }

}