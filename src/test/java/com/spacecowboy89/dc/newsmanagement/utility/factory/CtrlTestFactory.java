package com.spacecowboy89.dc.newsmanagement.utility.factory;

import com.spacecowboy89.dc.newsmanagement.utility.constant.CategoryCtrlConstants;
import com.spacecowboy89.dc.newsmanagement.utility.constant.JournalistCtrlConstants;
import com.spacecowboy89.dc.newsmanagement.utility.constant.NewsCtrlConstants;
import com.spacecowboy89.dc.newsmanagement.utility.constant.UserCtrlConstants;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Journalist;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class CtrlTestFactory {

    @Bean("news-instance")
    public News getNews() {
        return new News(NewsCtrlConstants.NEWSCODE_SAMPLE_1,
                NewsCtrlConstants.TITLE_SAMPLE_1,
                NewsCtrlConstants.SUMMARY_SAMPLE_1,
                NewsCtrlConstants.POSITIVE_VOTE_SAMPLE_1,
                NewsCtrlConstants.NEGATIVE_VOTE_SAMPLE_1);
    }


    @Bean("news-list-instance")
    public List<News> getNewsList() {
        List<News> newsList = new ArrayList<>();
        newsList.add(
                new News(NewsCtrlConstants.NEWSCODE_SAMPLE_1,
                        NewsCtrlConstants.TITLE_SAMPLE_1,
                        NewsCtrlConstants.SUMMARY_SAMPLE_1,
                        NewsCtrlConstants.POSITIVE_VOTE_SAMPLE_1,
                        NewsCtrlConstants.NEGATIVE_VOTE_SAMPLE_1
                ));

        newsList.add(
                new News(NewsCtrlConstants.NEWSCODE_SAMPLE_2,
                        NewsCtrlConstants.TITLE_SAMPLE_2,
                        NewsCtrlConstants.SUMMARY_SAMPLE_2,
                        NewsCtrlConstants.POSITIVE_VOTE_SAMPLE_2,
                        NewsCtrlConstants.NEGATIVE_VOTE_SAMPLE_2));

        newsList.add(
                new News(NewsCtrlConstants.NEWSCODE_SAMPLE_3,
                        NewsCtrlConstants.TITLE_SAMPLE_3,
                        NewsCtrlConstants.SUMMARY_SAMPLE_3,
                        NewsCtrlConstants.POSITIVE_VOTE_SAMPLE_3,
                        NewsCtrlConstants.NEGATIVE_VOTE_SAMPLE_3));

        return newsList;
    }


    @Bean("user-instance")
    public User getUser() {
        return new User(
                UserCtrlConstants.NAME_SAMPLE_1,
                UserCtrlConstants.SURNAME_SAMPLE_1,
                UserCtrlConstants.EMAIL_SAMPLE_1,
                UserCtrlConstants.USERNAME_SAMPLE_1,
                UserCtrlConstants.PASSWORD_SAMPLE_1,
                UserCtrlConstants.USERCODE_SAMPLE_1
        );
    }


    @Bean("users-instance")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(
                new User(
                        UserCtrlConstants.NAME_SAMPLE_1,
                        UserCtrlConstants.SURNAME_SAMPLE_1,
                        UserCtrlConstants.EMAIL_SAMPLE_1,
                        UserCtrlConstants.USERNAME_SAMPLE_1,
                        UserCtrlConstants.PASSWORD_SAMPLE_1,
                        UserCtrlConstants.USERCODE_SAMPLE_1
                )
        );

        users.add(
                new User(
                        UserCtrlConstants.NAME_SAMPLE_2,
                        UserCtrlConstants.SURNAME_SAMPLE_2,
                        UserCtrlConstants.EMAIL_SAMPLE_2,
                        UserCtrlConstants.USERNAME_SAMPLE_2,
                        UserCtrlConstants.PASSWORD_SAMPLE_2,
                        UserCtrlConstants.USERCODE_SAMPLE_2)
        );

        users.add(
                new User(
                        UserCtrlConstants.NAME_SAMPLE_3,
                        UserCtrlConstants.SURNAME_SAMPLE_3,
                        UserCtrlConstants.EMAIL_SAMPLE_3,
                        UserCtrlConstants.USERNAME_SAMPLE_3,
                        UserCtrlConstants.PASSWORD_SAMPLE_3,
                        UserCtrlConstants.USERCODE_SAMPLE_3)
        );

        return users;
    }


    @Bean("category-instance")
    public Category getCategory(@Autowired @Qualifier("news-list-instance") List<News> newsList) {
        Category category = new Category(
                CategoryCtrlConstants.NAME_SAMPLE_1,
                CategoryCtrlConstants.CODE_SAMPLE_1
        );
        category.setNewsList(newsList);

        return category;
    }


    @Bean("categories-instance")
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        categories.add(
                new Category(
                        CategoryCtrlConstants.NAME_SAMPLE_1,
                        CategoryCtrlConstants.CODE_SAMPLE_1
                )
        );
        categories.add(
                new Category(
                        CategoryCtrlConstants.NAME_SAMPLE_2,
                        CategoryCtrlConstants.CODE_SAMPLE_2
                )
        );
        categories.add(
                new Category(
                        CategoryCtrlConstants.NAME_SAMPLE_3,
                        CategoryCtrlConstants.CODE_SAMPLE_3
                )
        );

        return categories;
    }

    @Bean("categories-instance-opt")
    public Optional<List<Category>> getCategoriesOpt(@Autowired @Qualifier("categories-instance") List<Category> categories){
        return Optional.ofNullable(categories);
    }


    @Bean("journalist-instance")
    public Journalist getJournalist(@Autowired @Qualifier("news-list-instance") List<News> newsList) {
        Journalist journalist = new Journalist(
                JournalistCtrlConstants.NAME_SAMPLE_1,
                JournalistCtrlConstants.SURNAME_SAMPLE_1,
                JournalistCtrlConstants.EMAIL_SAMPLE_1,
                JournalistCtrlConstants.USERNAME_SAMPLE_1,
                JournalistCtrlConstants.PASSWORD_SAMPLE_1,
                JournalistCtrlConstants.JOURNALIST_CODE_SAMPLE_1
        );

        journalist.setNewsList(newsList);
        return journalist;
    }
}