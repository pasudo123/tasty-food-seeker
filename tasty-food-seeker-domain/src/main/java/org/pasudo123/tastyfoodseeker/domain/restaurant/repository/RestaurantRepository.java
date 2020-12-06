package org.pasudo123.tastyfoodseeker.domain.restaurant.repository;

import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
