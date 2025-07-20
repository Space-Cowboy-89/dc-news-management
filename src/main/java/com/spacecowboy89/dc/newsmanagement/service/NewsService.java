package com.spacecowboy89.dc.newsmanagement.service;

import com.spacecowboy89.dc.newsmanagement.dto.MainInfoNewsDto;
import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.NewsDao;
import com.spacecowboy89.dc.newsmanagement.utility.mapper.NewsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NewsService {
    private NewsMapper newsMapper;
    private NewsDao newsDao;


    @Autowired
    public NewsService(NewsDao newsDao, NewsMapper newsMapper) {
        this.newsDao = newsDao;
        this.newsMapper = newsMapper;
    }

    /**
     * Method return last fifteen news in db
     *
     * @return
     */
    public List<MainInfoNewsDto> retrieveLast15MainInfoNews() {
        return newsMapper.toMainInfoNewsDtoList(newsDao
                .findLast15News()
                .orElseThrow( NoResFoundInDBException ::new ));
    }
}
