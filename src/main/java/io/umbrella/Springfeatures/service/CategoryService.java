package io.umbrella.Springfeatures.service;

import io.umbrella.Springfeatures.models.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category findById(String ID);

    Category save(Category category);

    void deleteById(String id);
}
