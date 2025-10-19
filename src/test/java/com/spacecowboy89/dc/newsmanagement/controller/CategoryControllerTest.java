package com.spacecowboy89.dc.newsmanagement.controller;

import com.spacecowboy89.dc.newsmanagement.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryControllerTest {
    private final MockMvc mockMvc;
    private final CategoryService categoryService;

    @Autowired
    public CategoryControllerTest(MockMvc mockMvc, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.mockMvc = mockMvc;
    }

    @Test
    public void subcategoriesById() throws Exception {
/*
        mockMvc.perform(get("/category/subcategoriesById")
                .param("categoryId","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(3));
 */
    }


}
