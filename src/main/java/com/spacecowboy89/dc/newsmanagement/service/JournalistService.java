package com.spacecowboy89.dc.newsmanagement.service;


import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.JournalistDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Journalist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JournalistService {
    private final JournalistDao journalistDao;

    @Autowired
    public JournalistService(JournalistDao journalistDao) {
        this.journalistDao = journalistDao;
    }

    public Journalist retrieveById(String journalistCode){
        return journalistDao.findById(journalistCode)
                .orElseThrow(NoResFoundInDBException::new);
    }
}
