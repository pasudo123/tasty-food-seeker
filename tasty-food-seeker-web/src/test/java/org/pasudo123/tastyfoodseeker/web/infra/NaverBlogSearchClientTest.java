package org.pasudo123.tastyfoodseeker.web.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pasudo123.tastyfoodseeker.web.config.ObjectMapperConfiguration;
import org.pasudo123.tastyfoodseeker.web.config.WebClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("dev")
@SpringBootTest(classes = {
        NaverBlogSearchClient.class,
})
@ExtendWith(SpringExtension.class)
@Import({WebClientConfiguration.class, ObjectMapperConfiguration.class})
@DisplayName("네이버 검색 클라이언트는")
class NaverBlogSearchClientTest {


    @Autowired
    private NaverBlogSearchClient naverBlogSearchClient;

    @Test
    @DisplayName("블로그를 검색한다.")
    public void getBlogsByApiTest() {

        // given
        final String query = "코레아노스 파워플랜트광화문점\t멕시코,남미음식";

        // when
        final Optional<Object> optional = naverBlogSearchClient.getBlogsByApi(query);

        // then
        assertThat(optional).isNotNull();
    }
}