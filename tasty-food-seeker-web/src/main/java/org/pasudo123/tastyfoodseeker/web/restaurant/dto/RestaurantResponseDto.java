package org.pasudo123.tastyfoodseeker.web.restaurant.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.pasudo123.tastyfoodseeker.domain.restaurant.pojo.AdditionalInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantResponseDto {

    private long id;
    private String name;
    private String category;
    private String guName;
    private String address;
    private String roadAddress;
    private AdditionalInfo additionalInfo;

    public RestaurantResponseDto(final Restaurant restaurant, final AdditionalInfo additionalInfo) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.category = restaurant.getCategory();
        this.guName = restaurant.getGu().getName();
        this.address = restaurant.getAddress();
        this.roadAddress = restaurant.getRoadAddress();
        this.additionalInfo = additionalInfo;
    }
}
