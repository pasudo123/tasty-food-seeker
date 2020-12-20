package org.pasudo123.tastyfoodseeker.domain.restaurant.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Gu {

    JONGNO_GU("종로구"),
    YONGSAN_GU("용산구"),
    GWANGJIN_GU("광진구"),
    JUNGNANG_GU("중랑구"),
    GANGBUK_GU("강북구"),
    NOWON_GU("노원구"),
    SEODAEMUN_GU("서대문구"),
    YANGCHEON_GU("양천구"),
    GURO_GU("구로구"),
    YEONGDEUNGPO_GU("영등포구"),
    GWANAK_GU("관악구"),
    GANGNAM_GU("강남구"),
    GANGDONG_GU("강동구"),
    SONGPA_GU("송파구"),
    SEOCHO_GU("서초구"),
    DONGJAK_GU("동작구"),
    GEUMCHEON_GU("금천구"),
    GANGSEO_GU("강서구"),
    MAPO_GU("마포구"),
    EUNPYEONG_GU("은평구"),
    DOBONG_GU("도봉구"),
    SEONGBUK_GU("성북구"),
    DONGDAEMUN_GU("동대문구"),
    SEONGDONG_GU("성동구"),
    JUNG_GU("중구"),

    NONE("없음"), ALL("전체");

    private final String name;

    public static Gu findGuByAddress(final String address) {
        return Arrays.stream(values())
                .filter(gu -> gu.isInclude(address))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isInclude(final String address) {
        return address.contains(this.name);
    }

    public boolean isAll() {
        return this == ALL;
    }
}
