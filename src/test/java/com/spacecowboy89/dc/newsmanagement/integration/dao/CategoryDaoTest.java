package com.spacecowboy89.dc.newsmanagement.integration.dao;

import com.spacecowboy89.dc.newsmanagement.utility.factory.CtrlTestFactory;
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

        category.setId(null);
    }


    @Test
    public void findByCategoryIdTest(@Autowired @Qualifier("categories-instance") List<Category> categories){
        Category categoryPersisted = categoryDao.persist(categories.get(0)).get();

        categories.get(1).setCategory(categoryPersisted);
        categories.get(2).setCategory(categoryPersisted);


        Category secondCategoryPersisted = categoryDao.persist(categories.get(1)).get();
        Category thirdCategoryPersisted = categoryDao.persist(categories.get(2)).get();

        List<Category> categoriesFinded = categoryDao.findByCategoryId(categoryPersisted.getId().intValue()).get();

        assert categoriesFinded.get(0).getName() == secondCategoryPersisted.getName();
        assert categoriesFinded.get(0).getCategoryCode() == secondCategoryPersisted.getCategoryCode();

        assert categoriesFinded.get(1).getName() == thirdCategoryPersisted.getName();
        assert categoriesFinded.get(1).getCategoryCode() == thirdCategoryPersisted.getCategoryCode();
    }


    @Test
    public void persistTest(@Autowired @Qualifier ("category-instance") Category category){
        Category categoryPersisted = categoryDao.persist(category).get();

        assert categoryPersisted.getName() == category.getName();
        assert categoryPersisted.getCategoryCode() == category.getCategoryCode();

        category.setId(null);
    }

}
