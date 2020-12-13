package org.pasudo123.tastyfoodseeker.crawl.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.pasudo123.tastyfoodseeker.crawl.config.ObjectMapperConfiguration;
import org.pasudo123.tastyfoodseeker.crawl.config.WebClientConfiguration;
import org.pasudo123.tastyfoodseeker.crawl.infra.NaverLocationSearchClient;
import org.pasudo123.tastyfoodseeker.crawl.infra.NaverSearchParser;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest(classes = {
        CrawlDataPipelineService.class,
        RestaurantSaveService.class,
        NaverLocationSearchClient.class,
        NaverSearchParser.class
})
@ExtendWith(SpringExtension.class)
@Import({WebClientConfiguration.class, ObjectMapperConfiguration.class})
@DisplayName("크롤링 데이터 파이프라이닝 서비스는")
class CrawlDataPipelineServiceTest {

    @Autowired private CrawlDataPipelineService crawlDataPipelineService;
    @Autowired private RestaurantSaveService restaurantSaveService;
    @Autowired private NaverLocationSearchClient naverLocationSearchClient;
    @Autowired private NaverSearchParser naverSearchParser;

    @MethodSource("provideUsageLocations")
    @ParameterizedTest
    @DisplayName("실행된다.")
    public void executeTest(final List<UsageLocation> usageLocations) {

        // when
        crawlDataPipelineService.execute(usageLocations);
    }

    static Stream<Arguments> provideUsageLocations() {
        return Stream.of(
                arguments(Arrays.asList(
                        new UsageLocation(LocalDate.of(2020, 11, 2), "훈민정(중구 서소문로 132)"),
                        new UsageLocation(LocalDate.of(2020, 11, 3), "참숯골(중구 무교로 16)"),
                        new UsageLocation(LocalDate.of(2020, 11, 3), "바닷가작은부엌덕수궁점(중구 덕수궁길 7)"))
                )
        );
    }
}