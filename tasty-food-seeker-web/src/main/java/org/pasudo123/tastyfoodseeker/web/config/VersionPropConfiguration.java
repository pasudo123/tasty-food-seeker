package org.pasudo123.tastyfoodseeker.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:version.properties", ignoreResourceNotFound = true)
public class VersionPropConfiguration {
}
