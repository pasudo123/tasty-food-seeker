package org.pasudo123.tastyfoodseeker.configserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	private static final String PROPS_LOCATION = "spring.config.additional-location="
			+ "file:/data/etc/application-override.yml";

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = new SpringApplicationBuilder(ConfigServerApplication.class)
				.properties(PROPS_LOCATION)
				.run(args);
	}
}
