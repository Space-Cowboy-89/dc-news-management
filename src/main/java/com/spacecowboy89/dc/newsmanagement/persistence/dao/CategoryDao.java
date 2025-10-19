package com.spacecowboy89.dc.newsmanagement.persistence.dao;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import com.spacecowboy89.dc.newsmanagement.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryDao {
    private CategoryRepository categoryRepo;

    @Autowired
    public CategoryDao(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Optional<Category> findByCategoryCode (String categoryCode){
        return categoryRepo.findByCategoryCode(categoryCode);
    }

    public Optional<List<Category>> findByCategoryId(int categoryId){
        return categoryRepo.findByCategoryId(categoryId);
    }
}
