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

    private static final double BASE_SIM = 0.5D;
    private List<NaverLocationItem> items;

    /**
     * naver search openapi 로 검색한 결과값을 텍스트 유사도를 통해서 하나의 결과로 리턴한다.
     * @param info
     * @return
     */
    public Optional<NaverLocationItem> findClosestAddressByInfo(final UsageLocationInfo info) {

        double maxSim = 0.0;
        NaverLocationItem findItem = null;

        for(NaverLocationItem item : items) {
            final String address = item.getAddress();
            final String loadAddress = item.getRoadAddress();

            final double addressSimValue = TextSimilarityAlgorithm.getSimByJaroWinkler(address, info.getAddress());
            final double loadAddressSimValue = TextSimilarityAlgorithm.getSimByJaroWinkler(loadAddress, info.getAddress());

            // 유사도가 절반도 안되는 경우
            if(addressSimValue <= BASE_SIM || loadAddressSimValue <= BASE_SIM) {
                continue;
            }

            if(maxSim < addressSimValue + loadAddressSimValue) {
                maxSim = addressSimValue + loadAddressSimValue;
                findItem = item;
            }
        }

        return Optional.ofNullable(findItem);
    }
}
