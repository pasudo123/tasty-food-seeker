package org.pasudo123.tastyfoodseeker.web.restaurant.controller;

import lombok.RequiredArgsConstructor;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.PageRequestDto;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.RestaurantResponseDto;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.RestaurantResponseDtos;
import org.pasudo123.tastyfoodseeker.web.restaurant.service.RestaurantFetchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantFetchService fetchService;

    @GetMapping
    public ResponseEntity<RestaurantResponseDtos> fetchByGu(@RequestParam(value = "gu") String gu, PageRequestDto pageRequestDto) {
        return ResponseEntity
                .ok(fetchService.fetchListByGu(gu, pageRequestDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<RestaurantResponseDto> fetchOnById(@PathVariable("id") long id) {
        return ResponseEntity
                .ok(fetchService.fetchOneById(id));
    }
}
