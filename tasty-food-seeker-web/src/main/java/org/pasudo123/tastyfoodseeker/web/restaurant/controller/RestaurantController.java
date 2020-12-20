package org.pasudo123.tastyfoodseeker.web.restaurant.controller;

import lombok.RequiredArgsConstructor;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.*;
import org.pasudo123.tastyfoodseeker.web.restaurant.service.RestaurantBlogFetchService;
import org.pasudo123.tastyfoodseeker.web.restaurant.service.RestaurantFetchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantFetchService fetchService;
    private final RestaurantBlogFetchService blogFetchService;

    @GetMapping
    public ResponseEntity<RestaurantResponseDtos> fetchByGu(@RequestParam(value = "gu") String gu,
                                                            PageRequestDto pageRequestDto) {
        return ResponseEntity
                .ok(fetchService.fetchListByGu(gu, pageRequestDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<RestaurantLatLngResponseDto> fetchOnById(@PathVariable("id") long id) {
        return ResponseEntity
                .ok(fetchService.fetchOneById(id));
    }

    @GetMapping("/blog")
    public ResponseEntity<RestaurantBlogResponseDtos> fetchBlogByName(@RequestParam(value = "name") String name) {
        return ResponseEntity
                .ok(blogFetchService.fetchListByName(name));
    }
}
