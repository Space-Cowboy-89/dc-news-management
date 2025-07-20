package com.spacecowboy89.dc.newsmanagement.persistence.dao;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import com.spacecowboy89.dc.newsmanagement.persistence.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class NewsDao {
    private NewsRepository newsRepo;

    @Autowired
    public NewsDao(NewsRepository newsRepo) {
        this.newsRepo = newsRepo;
    }

    public Optional<List<News>> findLast15News(){
        return newsRepo.findLast15News();
    }

}
