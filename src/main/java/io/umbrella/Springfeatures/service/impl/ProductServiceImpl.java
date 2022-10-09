package io.umbrella.Springfeatures.service.impl;

import io.umbrella.Springfeatures.models.Product;
import io.umbrella.Springfeatures.repository.ProductRepository;
import io.umbrella.Springfeatures.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getAllProductsByCategoryId(String categoryId, Pageable pageable) {
        return productRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Product> getAllProductsByBrandId(String brandId, Pageable pageable) {
        return productRepository.findAllByBrandId(brandId, pageable);
    }

    @Override
    public Product getProductById(String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Product not found");
        }
        return optionalProduct.get();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
