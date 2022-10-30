package io.umbrella.Springfeatures.config.dbmigrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import io.umbrella.Springfeatures.models.ERole;
import io.umbrella.Springfeatures.models.Role;
import io.umbrella.Springfeatures.repository.RoleRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ChangeUnit(id="role-initializer", order = "1", author = "mongock")
public class InitialSetupMigrations {

    private final MongoTemplate mongoTemplate;

    public InitialSetupMigrations(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void initializeRoles(){
        Role admin = new Role();
        admin.setId("ADMIN");
        admin.setName(ERole.ROLE_ADMIN);

        Role user = new Role();
        user.setId("USER");
        user.setName(ERole.ROLE_USER);

        Role moderator = new Role();
        moderator.setId("MODERATOR");
        moderator.setName(ERole.ROLE_MODERATOR);

        List<Role> roleList = Arrays.asList(admin, user, moderator);
        mongoTemplate.save(roleList);
    }

    @RollbackExecution
    public void rollbackExecution(RoleRepository mongoTemplate) {
        mongoTemplate.deleteAll();
    }
}
