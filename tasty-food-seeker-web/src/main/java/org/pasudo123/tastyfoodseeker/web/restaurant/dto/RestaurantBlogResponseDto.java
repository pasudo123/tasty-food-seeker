package org.pasudo123.tastyfoodseeker.web.restaurant.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pasudo123.tastyfoodseeker.web.infra.pojo.blog.NaverBlogItem;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantBlogResponseDto {

    private String title;
    private String link;
    private String desc;
    private String date;

    public RestaurantBlogResponseDto(final NaverBlogItem item) {
        this.title = item.getTitle();
        this.link = item.getLink();
        this.desc = item.getDescription();
        this.date = item.getPostdate();
    }
}
