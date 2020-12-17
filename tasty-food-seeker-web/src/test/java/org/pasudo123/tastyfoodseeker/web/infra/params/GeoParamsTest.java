package org.pasudo123.tastyfoodseeker.web.infra.params;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GeoParams 객체는")
class GeoParamsTest {

    @Test
    @DisplayName("쿼리를 반환한다.")
    public void toQueryTest() {

        // given
        final String mapx = "1";
        final String mapy = "2";
        final GeoParams params = GeoParams.createParamsByKatech(mapx, mapy);

        // when
        final String queryParams = params.toQueryParams();

        // then
        assertThat(queryParams).isEqualTo("coords=1,2&sourcecrs=nhn:128&orders=roadaddr&output=json");
    }
}