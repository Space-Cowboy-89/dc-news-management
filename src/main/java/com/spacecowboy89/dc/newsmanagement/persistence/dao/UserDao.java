package com.spacecowboy89.dc.newsmanagement.persistence.dao;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import com.spacecowboy89.dc.newsmanagement.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao {
    private UserRepository userRepo;

    @Autowired
    public UserDao(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public Optional<User> persistUser(User user){
       return Optional.of(userRepo.save(user)) ;
    }


    public Optional<User> findUserByUserCode(String userCode){
        return userRepo.findByUserCode(userCode);
    }

    public Optional<Boolean> existByUserCode(String userCode){
        return Optional.of(userRepo.existUserByUserCode(userCode));
    }
}
