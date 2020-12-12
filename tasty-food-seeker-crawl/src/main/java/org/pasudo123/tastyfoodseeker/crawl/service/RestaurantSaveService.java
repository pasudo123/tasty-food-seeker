package org.pasudo123.tastyfoodseeker.crawl.service;

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

    private final RestaurantFetchService restaurantFetchService;
    private final RestaurantRepository restaurantRepository;

    public void saveAll(final List<Restaurant> restaurants) {
        for(Restaurant restaurant : restaurants) {
            if(restaurantFetchService.findRestaurantByHash(restaurant).isEmpty()){
                restaurantRepository.save(restaurant);
            }
        }
    }
}
