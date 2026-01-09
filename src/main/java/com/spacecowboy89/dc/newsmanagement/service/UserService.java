package com.spacecowboy89.dc.newsmanagement.service;


import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.UserDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                orElseThrow(NoResFoundInDBException::new);

        log.info("saveUser function executed successfully!");
        return user;
    }


    public User retrieveUserByUserCode(String userCode) {
        log.info("retrieveUserByUserCode function in execution!");
        User user = userDao.findUserByUserCode(userCode).orElseThrow(NoResFoundInDBException::new);

        log.info("retrieveUserByUserCode function executed successfully!");
        return user;
    }


    public Boolean existUserByUserCode(String userCode) {
        log.info("existUserByUserCode function in execution!");
        Boolean userExist = userDao.existByUserCode(userCode)
                .orElseThrow(NoResFoundInDBException::new);

        log.info("existUserByUserCode function executed successfully!");
        return userExist;
    }

}
