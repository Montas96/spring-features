package io.umbrella.Springfeatures.repository;

import io.umbrella.Springfeatures.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    Page<Product> findAllByCategoryId(String categoryId, Pageable pageable);

    Page<Product> findAllByBrandId(String brandId, Pageable pageable);

}
