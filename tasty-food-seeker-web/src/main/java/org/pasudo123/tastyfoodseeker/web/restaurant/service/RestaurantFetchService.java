package org.pasudo123.tastyfoodseeker.web.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Gu;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.pasudo123.tastyfoodseeker.domain.restaurant.repository.RestaurantRepository;
import org.pasudo123.tastyfoodseeker.web.exception.ErrorCode;
import org.pasudo123.tastyfoodseeker.web.exception.detail.EntityEmptyException;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.PageRequestDto;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.RestaurantResponseDto;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.RestaurantResponseDtos;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantFetchService {

    private final RestaurantConvertService convertService;
    private final RestaurantRepository restaurantRepository;

    public RestaurantResponseDtos fetchListByGu(final String gu, final PageRequestDto pageRequestDto) {
        final Gu currentGu = Enum.valueOf(Gu.class, gu);
        final Page<Restaurant> restaurantPages = restaurantRepository.fetchByGuAndPager(currentGu, pageRequestDto.toPageRequestByIdAsc());
        final List<RestaurantResponseDto> dtos = restaurantPages.getContent().stream()
                .map(convertService::entityToDto)
                .collect(Collectors.toList());

        return new RestaurantResponseDtos(dtos, restaurantPages);
    }

    public RestaurantResponseDto fetchOneById(final long id) {
        final Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityEmptyException(
                        ErrorCode.ENTITY_EMPTY_EXCEPTION,
                        String.valueOf(id).concat("를 통해 조회시 비어있습니다."),
                        Restaurant.class));

        return convertService.entityToDto(restaurant);
    }
}
