package com.spacecowboy89.dc.newsmanagement.controller.test;

import com.spacecowboy89.dc.newsmanagement.controller.constant.CategoryCtrlConstants;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryControllerTest {
    private final MockMvc mockMvc;
    @MockitoBean
    private final CategoryService categoryService;


    @Autowired
    public CategoryControllerTest(MockMvc mockMvc, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.mockMvc = mockMvc;
    }


    @Test
    public void getByCategory(@Autowired @Qualifier("categories-instance") List<Category> categories) throws Exception {
        when(categoryService.retrieveByCategoryId(any(Integer.class)))
                .thenReturn(categories);

        this.mockMvc.perform(get("/api/v1/categories/{categoryCode}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$[0].name").value(CategoryCtrlConstants.NAME_SAMPLE_1))
                .andExpect(jsonPath("$[1].name").value(CategoryCtrlConstants.NAME_SAMPLE_2))
                .andExpect(jsonPath("$[2].name").value(CategoryCtrlConstants.NAME_SAMPLE_3));
    }
}