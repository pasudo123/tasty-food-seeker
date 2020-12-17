package org.pasudo123.tastyfoodseeker.web.infra.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class NaverGeoAddressItem {
    @JsonProperty("y") private double lat;
    @JsonProperty("x") private double lng;
}
