package org.pasudo123.tastyfoodseeker.domain.restaurant.repository;

import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findBySha256Hash(final String hash);
}
