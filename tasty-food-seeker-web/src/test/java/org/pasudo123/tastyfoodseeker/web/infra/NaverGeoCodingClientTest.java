package org.pasudo123.tastyfoodseeker.web.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pasudo123.tastyfoodseeker.web.config.WebClientConfiguration;
import org.pasudo123.tastyfoodseeker.web.infra.pojo.NaverGeoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("dev")
@SpringBootTest(classes = {
        NaverGeoCodingClient.class
})
@ExtendWith(SpringExtension.class)
@Import(WebClientConfiguration.class)
@DisplayName("NaverGeoCodingClient 는")
class NaverGeoCodingClientTest {

    @Autowired
    private NaverGeoCodingClient geoCodingClient;

    @Test
    @DisplayName("주소를 좌표로 변환한다.")
    public void getGeoByApiTest() {

        // given
        final String query = "서울특별시 노원구 동일로203가길 29 브라운스톤중계 101호";

        // when
        final Optional<NaverGeoItem> itemOptional = geoCodingClient.getGeoByApi(query);

        // then
        assertThat(itemOptional).isNotNull();
    }

}