package com.spacecowboy89.dc.newsmanagement.unittest.service;

import com.spacecowboy89.dc.newsmanagement.persistence.dao.CategoryDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import com.spacecowboy89.dc.newsmanagement.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryServiceTest {
    private final MockMvc mockMvc;
    private final CategoryService categoryService;
    private final String STR_SAMPLE ="str";

    @MockitoBean
    private  CategoryDao categoryDao;

    @Autowired
    public CategoryServiceTest(MockMvc mockMvc, CategoryService categoryService) {
        this.mockMvc = mockMvc;
        this.categoryService = categoryService;
    }

    @Test
    public void retrieveByCategoryIdTest(@Autowired @Qualifier("categories-instance") List<Category> categories) throws Exception {
        when(categoryDao.findByCategoryId(1)).thenReturn(Optional.of(categories));

        List<Category> categoryResponse = categoryService.retrieveByCategoryId(1);

        assert categoryResponse.get(0).getCategoryCode() == categories.get(0).getCategoryCode();
        assert categoryResponse.get(1).getName() == categories.get(1).getName();
        assert  categoryResponse.get(2).getCategoryCode() == categories.get(2).getCategoryCode();
    }

    @Test
    public void retrieveByCategoryCode(@Autowired @Qualifier("category-instance") Category category) throws Exception {
        when (categoryDao.findByCategoryCode(STR_SAMPLE)).thenReturn(Optional.of(category));
        Category categoryResponse = categoryService.retrieveByCategoryCode(STR_SAMPLE);

        assert categoryResponse.getCategoryCode() == category.getCategoryCode();
        assert  categoryResponse.getName() == category.getName();
    }
}
