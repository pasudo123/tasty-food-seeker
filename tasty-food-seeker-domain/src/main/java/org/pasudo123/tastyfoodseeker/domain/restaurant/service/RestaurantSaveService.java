package org.pasudo123.tastyfoodseeker.domain.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.pasudo123.tastyfoodseeker.domain.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantSaveService {

    private final RestaurantRepository restaurantRepository;

    public void saveAll(final List<Restaurant> restaurants) {
        restaurantRepository.saveAll(restaurants);
    }
}
