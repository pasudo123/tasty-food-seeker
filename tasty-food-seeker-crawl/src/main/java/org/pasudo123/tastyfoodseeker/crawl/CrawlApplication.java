package org.pasudo123.tastyfoodseeker.crawl;

import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.crawl.component.TastyFoodSeekExplorer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(value = {"org.pasudo123.tastyfoodseeker.*"})
public class CrawlApplication {

	private static final String OPT_YEAR = "crawl.year";
	private static final String OPT_MONTH = "crawl.month";

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(CrawlApplication.class)
				.web(WebApplicationType.NONE) 	// web application NONE 타입
				.run(args);						// command-line args 추가.

		TastyFoodSeekExplorer bean = context.getBean(TastyFoodSeekExplorer.class);
		bean.closeAndQuitBrowser();
		context.close();
	}

	@Bean
	public ApplicationRunner applicationRunner(final TastyFoodSeekExplorer explorer) {
		return args -> {
			final int year = Integer.parseInt(args.getOptionValues(OPT_YEAR).get(0));
			final int month = Integer.parseInt(args.getOptionValues(OPT_MONTH).get(0));
			log.info("=============================");
			log.info(":: crawling date :: {}", String.format("%4d/%2d", year, month));
			log.info("=============================\n");
		};
	}
}
