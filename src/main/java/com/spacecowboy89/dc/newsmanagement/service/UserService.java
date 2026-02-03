package com.spacecowboy89.dc.newsmanagement.service;


import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.exception.PersistenceDataException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.UserDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public User saveUser(User user) {
        log.info("saveUser function in execution!");

        user = userDao.persistUser(user).
                orElseThrow(
                        ()-> new PersistenceDataException(
                                "Data persistence error.",
                                LocalDateTime.now()));

        log.info("saveUser function executed successfully!");
        return user;
    }


    public User retrieveUserByUserCode(String userCode) {
        log.info("retrieveUserByUserCode function in execution!");

        User user = userDao.findUserByUserCode(userCode)
                .orElseThrow(
                        ()-> new NoResFoundInDBException(
                                "User with user code \""+userCode+"\" not present.",
                                LocalDateTime.now()));

        log.info("retrieveUserByUserCode function executed successfully!");
        return user;
    }


    public Boolean existUserByUserCode(String userCode) {
        log.info("existUserByUserCode function in execution!");

        Boolean userExist = userDao.existByUserCode(userCode)
                .orElseThrow(
                        ()-> new NoResFoundInDBException(
                                "User with user code \""+userCode+"\" not present.",
                                LocalDateTime.now()));

        log.info("existUserByUserCode function executed successfully!");
        return userExist;
    }

    public List<User> retrieveUserDeleted(){
        log.info("retrieveUserDeleted function in execution.");

        List<User> users = userDao.findByDeletedAtIsNotNull().get();
        if(users.isEmpty())
            throw new NoResFoundInDBException("User deleted not present.",
                    LocalDateTime.now());

        log.info("retrieveUserDeleted function executed successfully.");
        return users;
    }
}
