package org.pasudo123.tastyfoodseeker.web.restaurant.controller;

import lombok.RequiredArgsConstructor;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.RestaurantResponseDto;
import org.pasudo123.tastyfoodseeker.web.restaurant.service.RestaurantFetchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantFetchService fetchService;

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> fetchByGu(@RequestParam(value = "gu") String gu) {
        return ResponseEntity
                .ok(fetchService.fetchListByGu(gu));
    }
}
