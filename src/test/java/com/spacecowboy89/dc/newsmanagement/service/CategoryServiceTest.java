package com.spacecowboy89.dc.newsmanagement.service;

import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public class CategoryServiceTest {
    private CategoryService categoryService;

    @Autowired
    public CategoryServiceTest(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Test
    public void retrieveByCategoryIdTest200() {
        Category category = new Category("gnomo", "lamdalamdalamdalamda");
        Category categoryOutput = categoryService.retrieveByCategoryId(2).get(0);
        assertTrue(categoryOutput.secondEquals(category, categoryOutput));
    }

    @Test
    public void retrieveByCategoryIdTest400() {
        try {
            categoryService.retrieveByCategoryId(6);

        } catch (NoResFoundInDBException ex) {
            assertTrue(true);
        }
        assertTrue(false);
    }
}