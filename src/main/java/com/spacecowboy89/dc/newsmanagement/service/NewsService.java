package com.spacecowboy89.dc.newsmanagement.service;

import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.JournalistDao;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.NewsDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Journalist;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class NewsService {
    private final NewsDao newsDao;
    private final CategoryService categoryService;
    private final JournalistDao journalistDao;


    @Autowired
    public NewsService(CategoryService categoryService,  NewsDao newsDao, JournalistDao journalistDao) {
        this.categoryService = categoryService;
        this.journalistDao = journalistDao;
        this.newsDao = newsDao;
    }


    public List<News> retrieveByPosVtEqMajor(int minorBound){
        log.info("retrieveByPosVtEqMajor function in execution!");
        List<News> newsL = newsDao.findByPositiveVtEqMaj(minorBound).get();
        if(newsL.isEmpty())
            throw new NoResFoundInDBException(
                    "News with positive vote >="+minorBound+" not exist",
                    LocalDateTime.now());

        log.info("retrieveByPosVtEqMajor function executed successfully!");
        return newsL;
    }

    /**
     * Method return last fifteen news in db
     *
     * @return
     */
    public List<News> retrieveLast15News() {
        log.info("retrieveLast15NewsInfo function in execution!");
        List<News> newsL = newsDao.findLast15News().get();
        if(newsL.isEmpty())
            throw new NoResFoundInDBException(
                    "News in system not present.",
                    LocalDateTime.now());

        log.info("retrieveLast15News function executed successfully!");
        return newsL;
    }


    public News retrieveByNewsCode(String newsCode) {
        log.info("retrieveByNewsCode function in execution!");
        News news = newsDao
                .findNewsByNewsCode(newsCode)
                .orElseThrow(
                        ()-> new NoResFoundInDBException(
                                "News with news code \""+newsCode+"\" not present.",
                                LocalDateTime.now()
                        ));

        log.info("retrieveByNewsCode function executed successfully!");
        return news;
    }


    public List<News> retrieveByCategory(String categoryCode) {
        log.info("retrieveByCategory function in execution!");

        Category category = categoryService.retrieveByCategoryCode(categoryCode);
        List<News> newsL=category.getNewsList();
        if(newsL.isEmpty())
            throw new NoResFoundInDBException(
                    "News with category code \""+categoryCode+"\" not present.",
                    LocalDateTime.now());

        log.info("retrieveByCategory function executed successfully!");
        return newsL;
    }

    public List<News> retrieveByBeetwen2PublicationDate(LocalDateTime firstPublicationDate, LocalDateTime secondPublicationDate) {
        log.info("retrieveByBeetwen2PublicationDate function in execution!");

        List<News> newsL = newsDao.findByBetween2PublicationDate(firstPublicationDate, secondPublicationDate).get();
        if(newsL.isEmpty())
            throw new NoResFoundInDBException(
                    "News beetwen "+firstPublicationDate+" and "+secondPublicationDate+" dates not present.",
                    LocalDateTime.now());

        log.info("retrieveByBeetwen2PublicationDate function executed successfully!");
        return newsL;
    }


    public List<News> retrieveByJournalist(String journalistCode) {
        log.info("retrieveByJournalist function in execution!");

        Journalist journalist = journalistDao.findById(journalistCode)
                .orElseThrow(
                        () -> new NoResFoundInDBException("Jounalist with jounalist code \""+journalistCode+"\" not present.",
                                LocalDateTime.now()));

        List<News> newsL = journalist.getNewsList();
        if(newsL.isEmpty())
            throw new NoResFoundInDBException(
                    "News of journalist "+journalist.getName() +" "+journalist.getSurname()+" not present.",
                    LocalDateTime.now());

        log.info("retrieveByJournalist function executed successfully!");
        return newsL;
    }
}
