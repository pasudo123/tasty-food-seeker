package org.pasudo123.tastyfoodseeker.crawl.component;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pasudo123.tastyfoodseeker.crawl.config.SeleniumConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        TastyFoodSeekExplorer.class,
        TastyFoodSeekCrawler.class
})
@Import(SeleniumConfiguration.class)
@DisplayName("TastyFoodSeekCrawler 는")
public class TastyFoodSeekExplorerTest {

    @Autowired
    private TastyFoodSeekExplorer explorer;

    @Test
    @DisplayName("크롤을 진행한다.")
    public void doExploreTest() {
        explorer.doExploring(2020, 10);
    }
}
