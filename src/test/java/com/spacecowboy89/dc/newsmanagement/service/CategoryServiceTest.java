package com.spacecowboy89.dc.newsmanagement.service;

import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import com.spacecowboy89.dc.newsmanagement.persistence.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@Slf4j
@SpringBootTest
public class CategoryServiceTest {

    @InjectMocks
    private final CategoryService categoryService;
    @Mock
    private final CategoryRepository categoryRepo;


    @Autowired
    public CategoryServiceTest(CategoryService categoryService, CategoryRepository categoryRepo) {
        this.categoryService = categoryService;
        this.categoryRepo = categoryRepo;
    }


    @Test
    public void testRetrieveByCategoryId(@Autowired @Qualifier("category-instance") Category category) {
        //when(categoryRepo.findByCategoryId(2)).thenReturn(Optional.of(category));
        //when(categoryRepo.findByCategoryId(1)).thenReturn();
    }
}