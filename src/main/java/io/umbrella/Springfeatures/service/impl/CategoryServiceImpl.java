package io.umbrella.Springfeatures.service.impl;

import io.umbrella.Springfeatures.models.Category;
import io.umbrella.Springfeatures.repository.CategoryRepository;
import io.umbrella.Springfeatures.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(String ID) {
        Optional<Category> optionalCategory = categoryRepository.findById(ID);
        if (!optionalCategory.isPresent()) {
            throw new RuntimeException("can not find category");
        }
        return optionalCategory.get();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }
}
