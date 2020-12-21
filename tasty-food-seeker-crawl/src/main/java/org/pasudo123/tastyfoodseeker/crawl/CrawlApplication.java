package org.pasudo123.tastyfoodseeker.crawl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.crawl.component.TastyFoodSeekExplorer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@ComponentScan(value = {"org.pasudo123.tastyfoodseeker.*"})
public class CrawlApplication implements CommandLineRunner {

	private final TastyFoodSeekExplorer explorer;

	public static void main(String[] args) {
		SpringApplication.run(CrawlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("do crawl");
//		explorer.doExploring(2020, 10);
	}
}
