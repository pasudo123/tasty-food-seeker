package org.pasudo123.tastyfoodseeker.web.infra.pojo.geo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class NaverGeoItems {

    @JsonProperty("addresses")
    private List<NaverGeoAddressItem> naverGeoAddressItems;

    public Optional<NaverGeoAddressItem> getFirstItemIfExist() {
        if(naverGeoAddressItems.size() > 0) {
            return Optional.of(naverGeoAddressItems.get(0));
        }

        return Optional.empty();
    }
}
