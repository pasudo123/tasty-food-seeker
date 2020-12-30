package org.pasudo123.tastyfoodseeker.crawl;

import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.crawl.component.TastyFoodSeekExplorer;
import org.pasudo123.tastyfoodseeker.slack.message.SlackMessenger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
//		TastyFoodSeekExplorer bean = context.getBean(TastyFoodSeekExplorer.class);
//		bean.closeAndQuitBrowser();
		context.close();
	}

	@Bean
	public ApplicationRunner applicationRunner(final TastyFoodSeekExplorer explorer, final SlackMessenger slackMessenger) {

		final String line = "=============================";

		return args -> {

			final int year = Integer.parseInt(args.getOptionValues(OPT_YEAR).get(0));
			final int month = Integer.parseInt(args.getOptionValues(OPT_MONTH).get(0));
			final String crawlParam = String.format(":: crawling date :: %4d . %d", year, month);
			final String execDate = String.format(":: execution at : %s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

			log.info(line);
			log.info(crawlParam);
			log.info(execDate);
			log.info(line.concat("\n"));

			slackMessenger.postMessage(
					line.concat("\n")
					.concat(crawlParam).concat("\n")
					.concat(execDate).concat("\n")
					.concat(line));

			explorer.doExploring(year, month);
		};
	}
}
