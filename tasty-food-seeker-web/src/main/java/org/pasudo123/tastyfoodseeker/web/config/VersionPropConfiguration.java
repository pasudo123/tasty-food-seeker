package org.pasudo123.tastyfoodseeker.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:version.properties", ignoreResourceNotFound = true)
public class VersionPropConfiguration {
}
