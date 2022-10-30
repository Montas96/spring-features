package io.umbrella.Springfeatures.repository;

import io.umbrella.Springfeatures.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}

