package io.umbrella.Springfeatures.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    public ApplicationProperties() {
        System.out.println("ApplicationProperties");
    }
}
