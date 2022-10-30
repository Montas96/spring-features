package io.umbrella.Springfeatures.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MongoInitializer  implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static MongoDBContainer instance;

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        List<String> addedProperties = Arrays.asList(
                "spring.data.mongodb.uri=" + getContainer().getReplicaSetUrl(),
                "mongock.runner-type=ApplicationRunner"
        );
        TestPropertyValues.of(addedProperties).applyTo(context.getEnvironment());
    }

    private MongoDBContainer getContainer() {
        if (instance == null) {
            instance = new MongoDBContainer(DockerImageName.parse("mongo:4.4.12"))
                    .withReuse(true);
            instance.start();
        }
        return instance;
    }
}