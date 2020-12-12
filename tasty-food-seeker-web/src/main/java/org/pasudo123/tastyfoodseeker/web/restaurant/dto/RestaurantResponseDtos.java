package org.pasudo123.tastyfoodseeker.web.restaurant.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantResponseDtos {

    private List<RestaurantResponseDto> restaurantResponseDtos;
    private long totalElements;
    private long totalPages;

    public RestaurantResponseDtos(final List<RestaurantResponseDto> dtos, final Page<Restaurant> pages) {
        this.restaurantResponseDtos = dtos;
        this.totalElements = pages.getTotalElements();
        this.totalPages = pages.getTotalPages();
    }
}
