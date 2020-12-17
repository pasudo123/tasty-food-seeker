package org.pasudo123.tastyfoodseeker.web.restaurant.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.pasudo123.tastyfoodseeker.domain.restaurant.pojo.AdditionalInfo;
import org.pasudo123.tastyfoodseeker.web.infra.pojo.NaverGeoItem;

import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantResponseLatLngDto extends RestaurantResponseDto {

    private Double lat;
    private Double lng;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public RestaurantResponseLatLngDto(final Restaurant restaurant,
                                       final AdditionalInfo additionalInfo,
                                       final Optional<NaverGeoItem> optional) {
        super(restaurant, additionalInfo);
        optional.ifPresent(naverGeoItem -> naverGeoItem.getFirstItemIfExist()
                .ifPresentOrElse(naverGeoAddressItem -> {
                    this.lat = naverGeoAddressItem.getLat();
                    this.lng = naverGeoAddressItem.getLng();
                }, () -> {
                    this.lat = null;
                    this.lng = null;
                }));
    }
}
