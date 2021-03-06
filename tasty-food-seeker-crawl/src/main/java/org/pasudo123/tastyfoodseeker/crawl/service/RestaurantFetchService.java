package org.pasudo123.tastyfoodseeker.crawl.service;

import lombok.RequiredArgsConstructor;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.pasudo123.tastyfoodseeker.domain.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantFetchService {

    private final RestaurantRepository restaurantRepository;

    public Boolean isExistRestaurantByHash(final String hash) {
        return restaurantRepository.findBySha256Hash(hash)
                .isPresent();
    }

    public Optional<Restaurant> findRestaurantByHash(final Restaurant restaurant) {
        return restaurantRepository.findBySha256Hash(restaurant.getSha256Hash());
    }
}
