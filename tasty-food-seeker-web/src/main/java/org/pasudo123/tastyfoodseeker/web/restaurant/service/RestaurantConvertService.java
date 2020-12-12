package org.pasudo123.tastyfoodseeker.web.restaurant.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.pasudo123.tastyfoodseeker.domain.restaurant.pojo.AdditionalInfo;
import org.pasudo123.tastyfoodseeker.web.exception.ErrorCode;
import org.pasudo123.tastyfoodseeker.web.exception.detail.JsonConvertException;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.RestaurantResponseDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantConvertService {

    private final ObjectMapper mapper;

    public RestaurantResponseDto entityToDto(final Restaurant restaurant) {
        try {
            final AdditionalInfo additionalInfo = mapper.readValue(restaurant.getAdditionalInfo(), AdditionalInfo.class);
            return new RestaurantResponseDto(restaurant, additionalInfo);
        } catch (JsonProcessingException e) {
            throw new JsonConvertException(
                    ErrorCode.JSON_MAPPING_EXCEPTION,
                    e.getMessage(),
                    restaurant.getAdditionalInfo(),
                    AdditionalInfo.class);
        }
    }
}
