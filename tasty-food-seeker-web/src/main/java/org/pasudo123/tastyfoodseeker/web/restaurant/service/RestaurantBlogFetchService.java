package org.pasudo123.tastyfoodseeker.web.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.pasudo123.tastyfoodseeker.web.infra.NaverBlogSearchClient;
import org.pasudo123.tastyfoodseeker.web.infra.pojo.blog.NaverBlogItems;
import org.pasudo123.tastyfoodseeker.web.restaurant.dto.RestaurantBlogResponseDtos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantBlogFetchService {

    private final NaverBlogSearchClient blogSearchClient;

    public RestaurantBlogResponseDtos fetchListByName(final String name) {
        final Optional<NaverBlogItems> blogItemsOptional = blogSearchClient.getBlogsByApi(name);
        return new RestaurantBlogResponseDtos(blogItemsOptional);
    }
}
