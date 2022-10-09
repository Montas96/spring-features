package io.umbrella.Springfeatures.controllers;

import io.umbrella.Springfeatures.models.Category;
import io.umbrella.Springfeatures.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/category/{id}")
    public Category findOne(@PathVariable String id) {
        return categoryService.findById(id);
    }

    @PostMapping("/category")
    public Category create(@RequestBody Category category) {
        if (category.getId() != null) {
            throw new RuntimeException("new category can not has an id");
        }
        return categoryService.save(category);
    }

    @PutMapping("/category")
    public Category update(@RequestBody Category category) {
        if (category.getId() == null) {
            throw new RuntimeException("category must have an id");
        }
        return categoryService.save(category);
    }

    @DeleteMapping("/category/{id}")
    public void delete(@PathVariable String id) {
        categoryService.deleteById(id);
    }
}
