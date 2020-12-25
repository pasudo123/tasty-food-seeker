package org.pasudo123.tastyfoodseeker.crawl.infra.constants;

import java.util.HashSet;
import java.util.Set;

public class ExcludeItems {

    private ExcludeItems() { }

    public static final Set<String> NONE_RESTAURANTS = new HashSet<>(){{
        add("문구"); add("슈퍼"); add("주택"); add("숙박"); add("은행");
        add("여행"); add("명소"); add("산업"); add("기업"); add("보험");
        add("쇼핑"); add("전자"); add("가전"); add("교육"); add("의료"); add("건강");
        add("편의점"); add("예식장"); add("제조업"); add("우체국");
        add("장례식장"); add("공중전화");
        add("기술서비스"); add("금융서비스");
        add("정보서비스업");
        add("컴퓨터프로그래밍");
    }};
}
