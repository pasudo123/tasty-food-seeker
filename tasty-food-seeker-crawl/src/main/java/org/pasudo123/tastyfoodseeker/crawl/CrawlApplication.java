package org.pasudo123.tastyfoodseeker.crawl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.crawl.component.TastyFoodSeekExplorer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@ComponentScan(value = {"org.pasudo123.tastyfoodseeker.*"})
public class CrawlApplication implements CommandLineRunner {

	private final TastyFoodSeekExplorer explorer;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(CrawlApplication.class)
				.web(WebApplicationType.NONE).run();
		TastyFoodSeekExplorer bean = context.getBean(TastyFoodSeekExplorer.class);
		bean.closeAndQuitBrowser();
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("args : {}", Arrays.toString(Arrays.stream(args).toArray()));
		log.info("====> do crawl");
	}
}
