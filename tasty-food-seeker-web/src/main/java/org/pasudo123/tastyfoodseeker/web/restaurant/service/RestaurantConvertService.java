package org.pasudo123.tastyfoodseeker.web.restaurant.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.pasudo123.tastyfoodseeker.domain.restaurant.pojo.AdditionalInfo;
import org.pasudo123.tastyfoodseeker.web.exception.ErrorCode;
import org.pasudo123.tastyfoodseeker.web.exception.detail.JsonConvertException;
import org.pasudo123.tastyfoodseeker.web.infra.NaverGeoCodingClient;
import org.pasudo123.tastyfoodseeker.web.infra.pojo.NaverGeoItem;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.RestaurantResponseDto;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.RestaurantResponseLatLngDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantConvertService {

    private final ObjectMapper mapper;
    private final NaverGeoCodingClient geoCodingClient;

    private AdditionalInfo mappingRestaurantToAdditionalInfo(final Restaurant restaurant) {
        try {
            return mapper.readValue(restaurant.getAdditionalInfo(), AdditionalInfo.class);
        } catch (JsonProcessingException e) {
            throw new JsonConvertException(
                    ErrorCode.JSON_MAPPING_EXCEPTION,
                    e.getMessage(),
                    restaurant.getAdditionalInfo(),
                    AdditionalInfo.class);
        }
    }

    public RestaurantResponseDto entityToDto(final Restaurant restaurant) {
        return new RestaurantResponseDto(restaurant, mappingRestaurantToAdditionalInfo(restaurant));
    }

    public RestaurantResponseLatLngDto entityToDtoIncludeGeo(final Restaurant restaurant) {
        final AdditionalInfo additionalInfo = mappingRestaurantToAdditionalInfo(restaurant);
        final Optional<NaverGeoItem> geoItemOptional = geoCodingClient.getGeoByApi(restaurant.getRoadAddress());
        return new RestaurantResponseLatLngDto(restaurant, additionalInfo, geoItemOptional);
    }
}
