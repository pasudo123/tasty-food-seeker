package org.pasudo123.tastyfoodseeker.crawl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.crawl.component.TastyFoodSeekExplorer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@ComponentScan(value = {"org.pasudo123.tastyfoodseeker.*"})
public class CrawlApplication implements CommandLineRunner {

	private final TastyFoodSeekExplorer explorer;
	private final ConfigurableApplicationContext context;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(CrawlApplication.class)
				.web(WebApplicationType.NONE).run();
//		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("====> do crawl");
	}
}
