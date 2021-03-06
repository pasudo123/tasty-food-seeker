package org.pasudo123.tastyfoodseeker.crawl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.crawl.infra.NaverLocationSearchClient;
import org.pasudo123.tastyfoodseeker.crawl.infra.pojo.NaverLocationItem;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageLocation;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageLocationInfo;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlDataPipelineService {

    private final NaverLocationSearchClient naverLocationSearchClient;
    private final RestaurantSaveService restaurantSaveService;
    private final RestaurantFetchService restaurantFetchService;

    /**
     * (1) naver api 조회
     * (2) 조회된 결과와 가장 유사한 주소 결과를 1:1 매칭
     * (3) 엔티티로 변환
     * (4) save 수행토록 설정
     */
    public void execute(final List<UsageLocation> usageLocations) {
        final List<UsageLocationInfo> usageLocationInfos = usageLocations.stream()
                .map(UsageLocation::toRestaurantInfo)
                .collect(Collectors.toList());

        /**
         * 네이버 오픈 api 를 요청하기 전에 디비에서 해시값을 사전에 검사하면,
         * 요청 api 횟수를 줄일 수 있다. 그러나 디비의 select 를 여러번 조회해야 하는 문제가 발생한다.
         * trade off 는 어떻게 할 것인지 고민스럽다.
         */
//        restaurantFetchService.isExistRestaurantByHash()
//        Restaurant.getSha256Hash()

        final List<Restaurant> restaurants = new ArrayList<>();

        for(UsageLocationInfo info : usageLocationInfos) {
            naverLocationSearchClient.getLocationInfoByApi(info.getName())
                    .flatMap(naverLocationItems -> naverLocationItems.findClosestAddressByInfo(info))
                    .filter(NaverLocationItem::isNormalLengthAddress)
                    .filter(NaverLocationItem::isSeoulLocation)
                    .filter(NaverLocationItem::isRestaurant)
                    .ifPresent(naverLocationItem -> {
                        final Restaurant restaurant = naverLocationItem.toRestaurantEntity();
                        restaurants.add(restaurant);
                    });
        }

        restaurantSaveService.saveAll(restaurants);
    }
}
