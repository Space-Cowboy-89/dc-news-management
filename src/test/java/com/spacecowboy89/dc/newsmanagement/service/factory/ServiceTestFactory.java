package com.spacecowboy89.dc.newsmanagement.service.factory;


import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ServiceTestFactory {

    @Bean("service-category-instance")
    public Category getCategory(){
        Category category = new Category("catagoria1","xxxx");
        category.setId(1l);
        return category;
    }
}
