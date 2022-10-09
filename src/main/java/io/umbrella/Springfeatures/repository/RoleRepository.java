package io.umbrella.Springfeatures.repository;

import io.umbrella.Springfeatures.models.ERole;
import io.umbrella.Springfeatures.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
