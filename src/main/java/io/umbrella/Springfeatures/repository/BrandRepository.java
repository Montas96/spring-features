package io.umbrella.Springfeatures.repository;

import io.umbrella.Springfeatures.models.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrandRepository extends MongoRepository<Brand, String> {
}
