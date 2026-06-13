package com.spacecowboy89.dc.newsmanagement.controller.test;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import com.spacecowboy89.dc.newsmanagement.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final String HARDWARE = "hardware";
    private final String SOFTWARE = "software";
    private final String ANDROID = "android";
    private final int CATEGORYID = 1;


    @Autowired
    public CategoryControllerTest(MockMvc mockMvc, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.mockMvc = mockMvc;
    }

    @Test
    public void getSubcategoriesByCategory200() throws Exception {

        when(categoryService.retrieveByCategoryId(any(Integer.class)))
                .thenReturn(this.getCategories());

        this.mockMvc.perform(get("/category/subcategories")
                        .param("category-id",""+CATEGORYID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$[0].name").value(HARDWARE))
                .andExpect(jsonPath("$[1].name").value(SOFTWARE))
                .andExpect(jsonPath("$[2].name").value(ANDROID));
    }

    private List<Category> getCategories(){
        List<Category> categories = List.of(
                new Category(HARDWARE, "xxxzzzccc"),
                new Category(SOFTWARE, "qqqwwwe"),
                new Category(ANDROID, "sadjaio"));
        return categories;
    }
}
