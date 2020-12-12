package org.pasudo123.tastyfoodseeker.domain.restaurant.repository;

import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Gu;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RestaurantRepositoryCustom {

    Page<Restaurant> fetchByGuAndPager(final Gu paramGu, PageRequest pageRequest);
}
