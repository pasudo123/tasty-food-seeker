package org.pasudo123.tastyfoodseeker.configserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * in spring runtime get the version.properties file,
 * using @PropertySource add a value to the environment
 */
@Configuration
@PropertySource(value = "classpath:version.properties", ignoreResourceNotFound = true)
public class VersionPropConfiguration {
}
