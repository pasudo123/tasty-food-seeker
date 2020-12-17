package org.pasudo123.tastyfoodseeker.web.infra.params;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GeoParams {

    private final Map<String, Object> params = new LinkedHashMap<>();

    public static GeoParams createParamsByKatech(final String mapx, final String mapy) {
        GeoParams params = new GeoParams();
        params.setCoords(mapx, mapy);
        return params;
    }

    public boolean isEmpty() {
        return params.isEmpty();
    }

    private void setCoords(final String mapx, final String mapy) {
        params.put("coords", mapx.concat(",").concat(mapy));
        params.put("sourcecrs", "nhn:128");
        params.put("orders", "roadaddr");
        params.put("output", "json");
    }

    public String toQueryParams() {
        return params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }
}
