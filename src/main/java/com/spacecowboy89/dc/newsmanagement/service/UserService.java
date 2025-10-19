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
        user = userDao.persistUser(user).
                orElseThrow(NoResFoundInDBException::new);
        return user;

    }


    public User retrieveUserByUserCode(String userCode) {
        User user = userDao.findUserByUserCode(userCode).orElseThrow(NoResFoundInDBException::new);

        return user;
    }


    public Boolean existUserByUserCode(String userCode) {
        Boolean userExist = userDao.existByUserCode(userCode)
                .orElseThrow(NoResFoundInDBException::new);
        return userExist;
    }

}
