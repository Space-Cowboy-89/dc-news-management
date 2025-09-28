package com.spacecowboy89.dc.newsmanagement.persistence.dao;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import com.spacecowboy89.dc.newsmanagement.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    private UserRepository userRepo;

    @Autowired
    public UserDao(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public void persistUser(User user){
        User newUser = userRepo.save(user);
    }
}
