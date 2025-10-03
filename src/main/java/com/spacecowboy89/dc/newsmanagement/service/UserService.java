package com.spacecowboy89.dc.newsmanagement.service;


import com.spacecowboy89.dc.newsmanagement.dto.UserDto;
import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.UserDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import com.spacecowboy89.dc.newsmanagement.utility.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public UserDto saveUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.toUser(userDto);
        user = userDao.persistUser(user).orElseThrow();

        return UserMapper.INSTANCE.toUserDto(user);

    }


    public UserDto retrieveUserByUserCode(String userCode) {
        Optional<User> userOpt = userDao.findUserByUserCode(userCode);

        return UserMapper.INSTANCE.toUserDto(
                userOpt.orElseThrow(NoResFoundInDBException::new));
    }


    public Boolean existUserByUserCode(String userCode){
        return userDao.existByUserCode(userCode)
                .orElseThrow(NoResFoundInDBException::new) ;
    }


}
