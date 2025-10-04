package com.spacecowboy89.dc.newsmanagement.persistence.dao;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import com.spacecowboy89.dc.newsmanagement.persistence.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class NewsDao {
    private NewsRepository newsRepo;

    @Autowired
    public NewsDao(NewsRepository newsRepo) {
        this.newsRepo = newsRepo;
    }

    public Optional<List<News>> findLast15News(){
        return newsRepo.findLast15News();
    }

    public Optional<News> findNewsByNewsCode(String newsCode){
        return newsRepo.findByNewsCode(newsCode);
    }

    public Optional<List<News>> findByBetween2PublicationDate (LocalDateTime firstPublicationDate, LocalDateTime secondPublicationDate){
        return newsRepo.findByBetween2PublicationDate(firstPublicationDate,secondPublicationDate);
    }

}
