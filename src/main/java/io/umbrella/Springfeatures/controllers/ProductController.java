package io.umbrella.Springfeatures.controllers;

import io.umbrella.Springfeatures.models.Product;
import io.umbrella.Springfeatures.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Page<Product> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    public Product create(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new RuntimeException("product already has an id");
        }
        return productService.save(product);
    }

    @PutMapping("/product")
    public Product update(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("product does not have an id");
        }
        return productService.save(product);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable String id) {
        productService.delete(id);
    }


    @GetMapping("/products-by-category/{id}")
    public Page<Product> getAllProductsByCategory(@PathVariable String id, Pageable pageable) {
        return productService.getAllProductsByCategoryId(id, pageable);
    }

    @GetMapping("/products-by-brand/{id}")
    public Page<Product> getAllProductsByBrand(@PathVariable String id, Pageable pageable) {
        return productService.getAllProductsByBrandId(id, pageable);
    }
}
