package org.pasudo123.tastyfoodseeker.web.infra.pojo.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class NaverBlogItems {

    @JsonProperty("items")
    private List<NaverBlogItem> naverBlogItems;
}
