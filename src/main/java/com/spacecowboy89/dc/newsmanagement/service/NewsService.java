package com.spacecowboy89.dc.newsmanagement.service;

import com.spacecowboy89.dc.newsmanagement.dto.NewsDto;
import com.spacecowboy89.dc.newsmanagement.dto.NewsInfoDto;
import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.NewsDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Journalist;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import com.spacecowboy89.dc.newsmanagement.utility.mapper.NewsMapper;
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
    public NewsService(CategoryService categoryService,  JournalistService journalistService, NewsDao newsDao) {
        this.categoryService = categoryService;
        this.journalistService = journalistService;
        this.newsDao = newsDao;
    }



    /**
     * Method return last fifteen news in db
     *
     * @return
     */
    public List<NewsInfoDto> retrieveLast15NewsInfo() {
        return NewsMapper.INSTANCE.toNewsInfoDtoList(newsDao
                .findLast15News()
                .orElseThrow(NoResFoundInDBException::new));
    }


    public NewsDto retrieveByNewsCode(String newsCode) {
        return NewsMapper.INSTANCE.toNewsDto(
                newsDao.findNewsByNewsCode(newsCode)
                        .orElseThrow(NoResFoundInDBException::new));
    }

    public List<NewsDto> retrieveLast15Info() {
        return NewsMapper.INSTANCE.toNewsDtoList(
                newsDao.findLast15News()
                        .orElseThrow(NoResFoundInDBException::new));
    }


    public List<NewsDto> retrieveByCategory(String categoryCode) {
        Category category = categoryService.retrieveByCategoryCode(categoryCode);

        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(category.getNewsList());
        return newsDtoList;
    }

    public List<NewsDto> retrieveByBeetwen2PublicationDate (LocalDateTime firstPublicationDate, LocalDateTime secondPublicationDate){
        List<News> newsList = newsDao.findByBetween2PublicationDate(firstPublicationDate,secondPublicationDate)
                .orElseThrow(NoResFoundInDBException::new);
        return NewsMapper.INSTANCE.toNewsDtoList(newsList);
    }


    public List<News> retrieveByJournalist (String journalistCode){
        Journalist journalist = journalistService.retrieveById(journalistCode);
        return journalist.getNewsList();
    }
}
