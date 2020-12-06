package org.pasudo123.tastyfoodseeker.crawl.pojo;

import lombok.Getter;

/**
 * 서울기준 음식점
 */
@Getter
public class UsageLocationInfo {

    private static final String SEOUL_LOCATION = "서울";
    private static final String SEOUL_FULL_LOCATION = "서울특별시 ";

    private final String name;
    private final String address;

    public UsageLocationInfo(final String name, final String address) {
        this.name = name;
        this.address = address.contains(SEOUL_LOCATION)
                ? address
                : SEOUL_FULL_LOCATION.concat(address);
    }
}