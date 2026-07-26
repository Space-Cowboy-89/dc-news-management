package com.spacecowboy89.dc.newsmanagement.integration.dao;

import com.spacecowboy89.dc.newsmanagement.controller.factory.CtrlTestFactory;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.CategoryDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@DataJpaTest
@ActiveProfiles("test")
@Import({CategoryDao.class, CtrlTestFactory.class})
public class CategoryDaoTest {
    private CategoryDao categoryDao;

    @Autowired
    public CategoryDaoTest(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }


    @Test
    public void findByCategoryCodeTest (@Autowired @Qualifier("category-instance") Category category){

        Category categorySaved =categoryDao.persist(category).get();

        Category categoryFinded =categoryDao.findByCategoryCode(categorySaved.getCategoryCode()).get();

        assert categorySaved.getCategoryCode() == categoryFinded.getCategoryCode();
        assert categorySaved.getName() == categoryFinded.getName();
    }

    @Test
    public void findByCategoryIdTest(@Autowired @Qualifier("categories-instance") List<Category> categories){

    }

}
