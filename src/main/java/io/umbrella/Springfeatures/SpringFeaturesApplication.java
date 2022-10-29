package io.umbrella.Springfeatures;

import io.mongock.runner.springboot.EnableMongock;
import io.umbrella.Springfeatures.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableMongock
@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class SpringFeaturesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFeaturesApplication.class, args);
    }

}
