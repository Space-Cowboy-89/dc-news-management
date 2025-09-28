package com.spacecowboy89.dc.newsmanagement.service;


import com.spacecowboy89.dc.newsmanagement.persistence.dao.UserDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public boolean saveUser(User user){
        userDao.persistUser(user);
    }
}
