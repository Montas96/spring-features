package io.umbrella.Springfeatures;

import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoDatabase;
import io.mongock.test.springboot.junit5.MongockSpringbootJUnit5IntegrationTestBase;
import io.umbrella.Springfeatures.config.MongoInitializer;
import io.umbrella.Springfeatures.repository.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(initializers = MongoInitializer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MongockIntegrationTest extends MongockSpringbootJUnit5IntegrationTestBase {

    @Autowired
    private RoleRepository roleRepository;


    private MongoDatabase mongoDatabase;

    @Autowired
    private void initDatabase(MongoDatabaseFactory mongoDatabaseFactory) {
        mongoDatabase = mongoDatabaseFactory.getMongoDatabase();
    }

    @AfterEach
    void afterEach() {
        mongoDatabase.getCollection("roles").drop();
    }

    @Test
    void shouldSucceedFullyWhenCollectionIsNotCreatedYet() {
        executeMongock();
        Assertions.assertEquals(3, roleRepository.findAll().size());
    }

    @Test
    void shouldFailWhenCollectionIsAlreadyCreated() {
        MongoCommandException ex = Assertions.assertThrows(MongoCommandException.class, () -> mongoDatabase.createCollection("roles"));
        Assertions.assertTrue(ex.getMessage().contains("Collection already exists"));

    }

}