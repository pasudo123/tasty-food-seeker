package org.pasudo123.tastyfoodseeker.crawl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.crawl.infra.NaverSearchClient;
import org.pasudo123.tastyfoodseeker.crawl.pojo.RestaurantInfo;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlDataSaveService {

    private final NaverSearchClient naverSearchClient;

    public void saveAll(final List<UsageLocation> usageLocations) {
        final List<RestaurantInfo> RestaurantInfos = usageLocations.stream()
                .map(UsageLocation::toRestaurantInfo)
                .collect(Collectors.toList());


    }
}
