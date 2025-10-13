package com.spacecowboy89.dc.newsmanagement.service;

import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.CategoryDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public Category retrieveByCategoryCode(String categoryCode){
        return categoryDao.findByCategoryCode(categoryCode)
                .orElseThrow(NoResFoundInDBException::new);
    }
}
