package org.pasudo123.tastyfoodseeker.crawl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.SessionId;
import org.pasudo123.tastyfoodseeker.crawl.component.TastyFoodSeekExplorer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import javax.annotation.PreDestroy;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@ComponentScan(value = {"org.pasudo123.tastyfoodseeker.*"})
public class CrawlApplication implements CommandLineRunner {

	private final TastyFoodSeekExplorer explorer;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(CrawlApplication.class)
				.web(WebApplicationType.NONE).run();
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("====> do crawl");
		explorer.doExploring(2020, 10);
	}

	@PreDestroy
	public void beforeDestroy() {

	}
}
