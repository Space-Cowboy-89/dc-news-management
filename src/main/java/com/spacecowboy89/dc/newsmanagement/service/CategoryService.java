package com.spacecowboy89.dc.newsmanagement.service;

import com.spacecowboy89.dc.newsmanagement.exception.GeneralException;
import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.CategoryDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public Category retrieveByCategoryCode(String categoryCode) {
        Category category =  categoryDao.findByCategoryCode(categoryCode)
                .orElseThrow(
                        () ->  new NoResFoundInDBException(
                        "Category with categoryCode \""+categoryCode+"\" not present."
                        ,LocalDateTime.now()));

        return  category;
    }

    public List<Category> retrieveByCategoryId(int categoryId) {
        log.info("retrieveByCategoryId function in execution!");
        List<Category> categoryList = categoryDao.findByCategoryId(categoryId).get();
        if (categoryList.isEmpty())
            throw new NoResFoundInDBException(
                    "Categories with categoryId \""+categoryId+"\" not present.",
                    LocalDateTime.now());

        log.info("retrieveByCategoryId function executed successfully!");
        return categoryList;
    }
}
