package org.pasudo123.tastyfoodseeker.crawl.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pasudo123.tastyfoodseeker.crawl.config.ObjectMapperConfiguration;
import org.pasudo123.tastyfoodseeker.crawl.infra.pojo.NaverLocationItem;
import org.pasudo123.tastyfoodseeker.crawl.infra.pojo.NaverLocationItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        NaverSearchParser.class
})
@Import(ObjectMapperConfiguration.class)
@DisplayName("네이버 검색 파서는")
class NaverSearchParserTest {

    @Autowired
    private NaverSearchParser parser;

    @Test
    @DisplayName("지역 아이템을 획득한다.")
    public void getLocationItemsByResponseTest() {
        // given
        final String response = "{\n" +
                "\"lastBuildDate\": \"Sun, 06 Dec 2020 16:46:50 +0900\",\n" +
                "\"total\": 5,\n" +
                "\"start\": 1,\n" +
                "\"display\": 5,\n" +
                "\"items\": [\n" +
                "{\n" +
                "\"title\": \"<b>훈민정</b>\",\n" +
                "\"link\": \"\",\n" +
                "\"category\": \"한식>육류,고기요리\",\n" +
                "\"description\": \"\",\n" +
                "\"telephone\": \"\",\n" +
                "\"address\": \"서울특별시 중구 서소문동 93\",\n" +
                "\"roadAddress\": \"서울특별시 중구 서소문로 132-1\",\n" +
                "\"mapx\": \"309704\",\n" +
                "\"mapy\": \"551745\"\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"<b>훈민정</b>어학원\",\n" +
                "\"link\": \"http://blog.naver.com/kisiwi119\",\n" +
                "\"category\": \"교육,학문>교습학원\",\n" +
                "\"description\": \"\",\n" +
                "\"telephone\": \"\",\n" +
                "\"address\": \"경기도 구리시 토평동 964-6\",\n" +
                "\"roadAddress\": \"경기도 구리시 이문안로 134 거양빌딩 4층\",\n" +
                "\"mapx\": \"324802\",\n" +
                "\"mapy\": \"554648\"\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"<b>훈민정</b>필글씨교정학원\",\n" +
                "\"link\": \"http://www.xn--lg3b84tktkjob.com/\",\n" +
                "\"category\": \"교육,학문>글쓰기,독서지도\",\n" +
                "\"description\": \"\",\n" +
                "\"telephone\": \"\",\n" +
                "\"address\": \"대구광역시 달서구 진천동 511-2 금성빌딩 2층\",\n" +
                "\"roadAddress\": \"대구광역시 달서구 진천로 56 금성빌딩 2층\",\n" +
                "\"mapx\": \"447597\",\n" +
                "\"mapy\": \"357218\"\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"<b>훈민정</b>필공이소캘리그라피학원\",\n" +
                "\"link\": \"http://instagram.com/gongisocalli\",\n" +
                "\"category\": \"교육,학문>글쓰기,독서지도\",\n" +
                "\"description\": \"\",\n" +
                "\"telephone\": \"\",\n" +
                "\"address\": \"세종특별자치시 보람동 700-76\",\n" +
                "\"roadAddress\": \"세종특별자치시 남세종로 466 701호\",\n" +
                "\"mapx\": \"336433\",\n" +
                "\"mapy\": \"430891\"\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\"title\": \"<b>훈민정</b>필펜글씨교정교습소\",\n" +
                "\"link\": \"\",\n" +
                "\"category\": \"교육,학문>글쓰기,독서지도\",\n" +
                "\"description\": \"\",\n" +
                "\"telephone\": \"\",\n" +
                "\"address\": \"대전광역시 유성구 관평동 921\",\n" +
                "\"roadAddress\": \"대전광역시 유성구 관평2로 26 3층 훈민정필글씨교정\",\n" +
                "\"mapx\": \"345461\",\n" +
                "\"mapy\": \"425013\"\n" +
                "\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "\n" +
                "\n";

        // when
        final Optional<NaverLocationItems> optional = parser.getLocationItemsByResponse(response);

        // then
        assertThat(optional).isNotEmpty();
    }

}