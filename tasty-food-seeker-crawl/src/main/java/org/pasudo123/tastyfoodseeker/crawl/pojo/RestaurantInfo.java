package org.pasudo123.tastyfoodseeker.crawl.pojo;

import lombok.Getter;

@Getter
public class RestaurantInfo {

    private final String name;
    private final String address;

    public RestaurantInfo(final String name, final String address) {
        this.name = name;
        this.address = address;
    }
}