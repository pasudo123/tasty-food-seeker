package org.pasudo123.tastyfoodseeker.crawl.infra.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;
import org.pasudo123.tastyfoodseeker.crawl.pojo.RestaurantInfo;

import java.util.List;
import java.util.Optional;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverLocationItems {

    private List<NaverLocationItem> items;

    public Optional<NaverLocationItem> findClosestAddressByInfo(final RestaurantInfo info) {

        return Optional.empty();
    }
}
