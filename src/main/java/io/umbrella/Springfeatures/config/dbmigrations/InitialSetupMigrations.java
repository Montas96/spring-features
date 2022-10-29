package io.umbrella.Springfeatures.config.dbmigrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import io.umbrella.Springfeatures.models.ERole;
import io.umbrella.Springfeatures.models.Role;
import org.springframework.data.mongodb.core.MongoTemplate;

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
        mongoTemplate.save(admin);

        Role user = new Role();
        user.setId("USER");
        user.setName(ERole.ROLE_USER);
        mongoTemplate.save(user);

        Role moderator = new Role();
        moderator.setId("MODERATOR");
        moderator.setName(ERole.ROLE_MODERATOR);
        mongoTemplate.save(moderator);
    }

    @RollbackExecution
    public void rollbackExecution(MongoTemplate mongoTemplate) {
        mongoTemplate.getDb().drop();
    }
}
