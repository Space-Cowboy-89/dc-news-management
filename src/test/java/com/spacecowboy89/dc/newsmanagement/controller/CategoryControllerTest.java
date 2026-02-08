package com.spacecowboy89.dc.newsmanagement.controller;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import com.spacecowboy89.dc.newsmanagement.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



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
        List<Category> categories = categoryService.retrieveByCategoryId(1);

        String jsonResponse=mockMvc
                .perform(get("/category/subcategories?category-id=1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

*/
    }


}
