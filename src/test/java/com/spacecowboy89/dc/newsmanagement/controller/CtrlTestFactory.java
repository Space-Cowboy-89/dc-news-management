package com.spacecowboy89.dc.newsmanagement.controller;

import com.spacecowboy89.dc.newsmanagement.controller.constant.NewsCtrlConstants;
import com.spacecowboy89.dc.newsmanagement.controller.constant.UserCtrlConstants;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CtrlTestFactory {

    @Bean("news-instance")
    private News getNews() {
        return new News(NewsCtrlConstants.NEWSCODE_SAMPLE_1,
                NewsCtrlConstants.TITLE_SAMPLE_1,
                NewsCtrlConstants.SUMMARY_SAMPLE_1);
    }

    @Bean("news-list-instance")
    private List<News> getNewsList() {
        List<News> newsList = new ArrayList<>();
        newsList.add(
                new News(NewsCtrlConstants.NEWSCODE_SAMPLE_1,
                        NewsCtrlConstants.TITLE_SAMPLE_1,
                        NewsCtrlConstants.SUMMARY_SAMPLE_1));

        newsList.add(
                new News(NewsCtrlConstants.NEWSCODE_SAMPLE_2,
                        NewsCtrlConstants.TITLE_SAMPLE_2,
                        NewsCtrlConstants.SUMMARY_SAMPLE_2));

        newsList.add(
                new News(NewsCtrlConstants.NEWSCODE_SAMPLE_3,
                        NewsCtrlConstants.TITLE_SAMPLE_3,
                        NewsCtrlConstants.SUMMARY_SAMPLE_3));

        return newsList;
    }


    @Bean("user-instance")
    private User getUser() {
        return  new User(
                UserCtrlConstants.NAME_SAMPLE_1,
                UserCtrlConstants.SURNAME_SAMPLE_1,
                UserCtrlConstants.EMAIL_SAMPLE_1,
                UserCtrlConstants.USERNAME_SAMPLE_1,
                UserCtrlConstants.PASSWORD_SAMPLE_1
        );
    }


    @Bean("users-instance")
    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(
                new User(
                        UserCtrlConstants.NAME_SAMPLE_1,
                        UserCtrlConstants.SURNAME_SAMPLE_1,
                        UserCtrlConstants.EMAIL_SAMPLE_1,
                        UserCtrlConstants.USERNAME_SAMPLE_1,
                        UserCtrlConstants.PASSWORD_SAMPLE_1
                        )
        );

        users.add(
                new User(
                        UserCtrlConstants.NAME_SAMPLE_2,
                        UserCtrlConstants.SURNAME_SAMPLE_2,
                        UserCtrlConstants.EMAIL_SAMPLE_2,
                        UserCtrlConstants.USERNAME_SAMPLE_2,
                        UserCtrlConstants.PASSWORD_SAMPLE_2)
        );

        users.add(
                new User(
                        UserCtrlConstants.NAME_SAMPLE_3,
                        UserCtrlConstants.SURNAME_SAMPLE_3,
                        UserCtrlConstants.EMAIL_SAMPLE_3,
                        UserCtrlConstants.USERNAME_SAMPLE_3,
                        UserCtrlConstants.PASSWORD_SAMPLE_3)
        );

        return users;
    }
}