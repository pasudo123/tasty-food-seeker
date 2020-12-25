package org.pasudo123.tastyfoodseeker.crawl.infra.pojo;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;
import org.pasudo123.tastyfoodseeker.crawl.infra.constants.ExcludeItems;
import org.pasudo123.tastyfoodseeker.domain.restaurant.model.Restaurant;

import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
public class NaverLocationItem {

    private static final String SEOUL_LOCATION = "서울";

    private String title;
    private String link;
    private String category;
    private String description;
    private String telephone;
    private String address;
    private String roadAddress;
    private String mapx;
    private String mapy;

    /**
     * json 파싱시, html 태그문자는 삭제하고 값 세팅
     */
    @JsonSetter
    public void setTitle(String title) {
        title = title.replaceAll("<b>", Strings.EMPTY);
        title = title.replaceAll("</b>", Strings.EMPTY);
        this.title = title;
    }

    /**
     * 서울지역 여부
     */
    public boolean isSeoulLocation() {
        return address.contains(SEOUL_LOCATION);
    }

    /**
     * 음식점 여부
     */
    public boolean isRestaurant() {
        for(String name : ExcludeItems.NONE_RESTAURANTS) {
            if(category.contains(name)) {
                return false;
            }
        }

        return true;
    }

    public Restaurant toRestaurantEntity() {

        final String additionalInfo = "{"
                .concat("\"mapx\":").concat(mapx).concat(",")
                .concat("\"mapy\":").concat(mapy)
                .concat("}");

        return Restaurant.builder()
                .name(title)
                .category(category)
                .address(address)
                .roadAddress(roadAddress)
                .additionalInfo(additionalInfo)
                .build();
    }
}
