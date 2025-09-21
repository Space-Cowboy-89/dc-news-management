package com.spacecowboy89.dc.newsmanagement.service;

import com.spacecowboy89.dc.newsmanagement.dto.NewsDto;
import com.spacecowboy89.dc.newsmanagement.dto.NewsInfoDto;
import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.NewsDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import com.spacecowboy89.dc.newsmanagement.utility.mapper.CommentMapper;
import com.spacecowboy89.dc.newsmanagement.utility.mapper.EmployeeMapper;
import com.spacecowboy89.dc.newsmanagement.utility.mapper.NewsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NewsService {
    private NewsDao newsDao;


    @Autowired
    public NewsService(NewsDao newsDao) {
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


}
