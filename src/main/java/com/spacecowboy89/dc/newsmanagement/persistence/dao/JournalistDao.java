package com.spacecowboy89.dc.newsmanagement.persistence.dao;


import com.spacecowboy89.dc.newsmanagement.persistence.entity.Journalist;
import com.spacecowboy89.dc.newsmanagement.persistence.repository.JournalistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JournalistDao {
    private final JournalistRepository journalistRepo;

    @Autowired
    public JournalistDao(JournalistRepository journalistRepo) {
        this.journalistRepo = journalistRepo;
    }

    public Optional<Journalist> findById(String journalistCode){
        return  journalistRepo.findById(journalistCode);
    }
}
