package org.pasudo123.tastyfoodseeker.crawl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.crawl.infra.NaverSearchClient;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageLocationInfo;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageLocation;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlDataPipelineService {

    private final NaverSearchClient naverSearchClient;

    /**
     * (1) api 조회
     * (2) 조회된 결과와 가장 유사한 주소 결과를 1:1 매칭
     * (3) 엔티티로 변환
     * (4) save 수행토록 설정
     */
    public void execute(final List<UsageLocation> usageLocations) {
        final List<UsageLocationInfo> usageLocationInfos = usageLocations.stream()
                .map(UsageLocation::toRestaurantInfo)
                .collect(Collectors.toList());

        for(UsageLocationInfo info : usageLocationInfos) {
            naverSearchClient.getLocationInfoByApi(info.getName())
                    .flatMap(naverLocationItems -> naverLocationItems.findClosestAddressByInfo(info))
                    .ifPresent(naverLocationItem -> {
                        /** 엔티티 변환 및 save 수행하도록 설정 **/
                        final Restaurant restaurant = naverLocationItem.toRestaurantEntity();
                    });
        }
    }
}
