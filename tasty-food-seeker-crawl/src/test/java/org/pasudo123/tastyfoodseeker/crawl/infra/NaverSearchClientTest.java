package org.pasudo123.tastyfoodseeker.crawl.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pasudo123.tastyfoodseeker.crawl.config.ObjectMapperConfiguration;
import org.pasudo123.tastyfoodseeker.crawl.config.WebClientConfiguration;
import org.pasudo123.tastyfoodseeker.crawl.infra.pojo.NaverLocationItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("dev")
@SpringBootTest(classes = {
        NaverSearchClient.class,
        NaverSearchParser.class
})
@ExtendWith(SpringExtension.class)
@Import({WebClientConfiguration.class, ObjectMapperConfiguration.class})
@DisplayName("네이버 검색 클라이언트는")
class NaverSearchClientTest {

    @Autowired
    private NaverSearchClient naverSearchClient;

    @Autowired
    private NaverSearchParser naverSearchParser;

    @Test
    @DisplayName("api 를 통해서 위치정보를 획득한다.")
    public void getLocationInfoByApiTest() {
        // given
        final String query = "훈민정";

        // when
        final Optional<NaverLocationItems> optional = naverSearchClient.getLocationInfoByApi(query);

        // then
        assertThat(optional).isNotEmpty();
    }
}