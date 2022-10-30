package io.umbrella.Springfeatures.config.dbmigrations;

import io.umbrella.Springfeatures.models.Role;
import io.umbrella.Springfeatures.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("migration")
@DisplayName("InitialSetupMigrationsTest")
class InitialSetupMigrationsTest {
    @Mock
    private static MongoTemplate mongoTemplate;

    @InjectMocks
    private InitialSetupMigrations initialSetupMigrations;

    @BeforeEach
    void setUp() {
        //mongoTemplate = mock(MongoTemplate.class);
    }

    @Test
    @DisplayName("check roles are created")
    void initializeRoles() {
        initialSetupMigrations.initializeRoles();
        ArgumentCaptor<List<Role>> itemsCaptor = ArgumentCaptor.forClass(List.class);
        verify(mongoTemplate, new Times(1)).save(itemsCaptor.capture());
        assertEquals(3, itemsCaptor.getValue().size());

    }

    @Test
    @DisplayName("Check rollbackExecution")
    void rollbackExecution() {
        RoleRepository repository = mock(RoleRepository.class);
        initialSetupMigrations.rollbackExecution(repository);
        verify(repository, new Times(1)).deleteAll();
    }
}