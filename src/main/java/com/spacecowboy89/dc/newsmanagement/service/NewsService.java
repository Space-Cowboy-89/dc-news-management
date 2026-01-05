package com.spacecowboy89.dc.newsmanagement.service;

import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.NewsDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
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
    private final JournalistService journalistService;


    @Autowired
    public NewsService(CategoryService categoryService, JournalistService journalistService, NewsDao newsDao) {
        this.categoryService = categoryService;
        this.journalistService = journalistService;
        this.newsDao = newsDao;
    }


    /**
     * Method return last fifteen news in db
     *
     * @return
     */
    public List<News> retrieveLast15NewsInfo() {
        log.info("retrieveLast15NewsInfo of NewsService in execution!");
        List<News> newsList = newsDao.findLast15News().get();
        if(newsList.isEmpty())
            throw new NoResFoundInDBException();

        log.info("retrieveLast15NewsInfo of NewsService executed!");
        return newsList;
    }


    public News retrieveByNewsCode(String newsCode) {
        log.info("retrieveByNewsCode of NewsService in execution!");
        News news = newsDao.findNewsByNewsCode(newsCode)
                .orElseThrow(NoResFoundInDBException::new);

        return news;
    }


    public List<News> retrieveLast15Info() {
        List<News> newsList = newsDao.findLast15News().get();
        if(newsList.isEmpty())
            throw new NoResFoundInDBException();

        return newsList;

    }


    public List<News> retrieveByCategory(String categoryCode) {
        Category category = categoryService.retrieveByCategoryCode(categoryCode);
        List<News> newsList=category.getNewsList();
        if(newsList.isEmpty())
            throw new NoResFoundInDBException();

        return category.getNewsList();
    }

    public List<News> retrieveByBeetwen2PublicationDate(LocalDateTime firstPublicationDate, LocalDateTime secondPublicationDate) {
        List<News> newsList = newsDao.findByBetween2PublicationDate(firstPublicationDate, secondPublicationDate).get();
        if(newsList.isEmpty())
            throw new NoResFoundInDBException();

        return newsList;
    }


    public List<News> retrieveByJournalist(String journalistCode) {
        List<News> newsList = journalistService.retrieveById(journalistCode)
                .getNewsList();

        if(newsList.isEmpty())
            throw new NoResFoundInDBException();

        return newsList;
    }
}
