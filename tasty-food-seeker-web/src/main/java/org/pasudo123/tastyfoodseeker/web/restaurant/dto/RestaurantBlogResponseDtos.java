package org.pasudo123.tastyfoodseeker.web.restaurant.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pasudo123.tastyfoodseeker.web.infra.pojo.blog.NaverBlogItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantBlogResponseDtos {

    final List<RestaurantBlogResponseDto> restaurantBlogResponseDtos = new ArrayList<>();

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public RestaurantBlogResponseDtos(final Optional<NaverBlogItems> optional) {
        optional.ifPresent(naverBlogItems -> {
            naverBlogItems.getNaverBlogItems()
                    .forEach(naverBlogItem -> restaurantBlogResponseDtos.add(new RestaurantBlogResponseDto(naverBlogItem)));
        });
    }
}
