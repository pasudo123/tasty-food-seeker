package org.pasudo123.tastyfoodseeker.crawl.infra.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.crawl.pojo.UsageLocationInfo;
import org.pasudo123.tastyfoodseeker.util.TextSimilarityAlgorithm;

import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverLocationItems {

    private List<NaverLocationItem> items;

    public Optional<NaverLocationItem> findClosestAddressByInfo(final UsageLocationInfo info) {

        double maxSim = 0.0;
        NaverLocationItem findItem = null;

        for(NaverLocationItem item : items) {
            final String address = item.getAddress();
            final String loadAddress = item.getRoadAddress();

            final double addressSimValue = TextSimilarityAlgorithm.getSimByJaroWinkler(address, info.getAddress());
            final double loadAddressSimValue = TextSimilarityAlgorithm.getSimByJaroWinkler(address, info.getAddress());

            if(maxSim < addressSimValue + loadAddressSimValue) {
                maxSim = addressSimValue + loadAddressSimValue;
                findItem = item;
            }
        }

        return Optional.ofNullable(findItem);
    }
}
