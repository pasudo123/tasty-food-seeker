package org.pasudo123.tastyfoodseeker.web.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Gu;
import org.pasudo123.tastyfoodseeker.domain.restaurant.repository.RestaurantRepository;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.RestaurantResponseDto;
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

    public List<RestaurantResponseDto> fetchListByGu(final String gu) {
        final Gu currentGu = Enum.valueOf(Gu.class, gu);
        return restaurantRepository.findByGu(currentGu)
                .stream()
                .map(convertService::entityToDto)
                .collect(Collectors.toList());
    }
}
