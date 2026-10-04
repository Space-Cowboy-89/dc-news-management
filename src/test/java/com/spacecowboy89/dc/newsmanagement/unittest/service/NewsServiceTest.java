package com.spacecowboy89.dc.newsmanagement.unittest.service;


import com.spacecowboy89.dc.newsmanagement.persistence.dao.CategoryDao;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.JournalistDao;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.NewsDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Journalist;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import com.spacecowboy89.dc.newsmanagement.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NewsServiceTest {
    @MockitoBean
    private NewsDao newsDao;
    @MockitoBean
    private JournalistDao journalistDao;
    @MockitoBean
    private CategoryDao categoryDao;

    private NewsService newsService;
    private final String STR_SAMPLE = "str";
    private final int NUM_SAMPLE=2;


    @Autowired
    public NewsServiceTest(NewsService newsService) {
        this.newsService = newsService;
    }

    @Test
    public void retrieveByJournalistTest(@Autowired @Qualifier("journalist-instance") Journalist journalist,
                                         @Autowired @Qualifier("news-list-instance") List<News> newsList) {
        when(journalistDao.findById(this.STR_SAMPLE)).thenReturn(Optional.of(journalist));

        List<News> jorunalistNewsRetrieved = newsService.retrieveByJournalist(this.STR_SAMPLE);
        this.compareTwoNewsList(jorunalistNewsRetrieved, newsList);
    }


    @Test
    public void retrieveByBeetwen2PublicationDateTest(@Autowired @Qualifier("news-list-instance") List<News> newsList) {
        final LocalDateTime firstPublicationDate = LocalDateTime.now();
        final LocalDateTime secondPublicationDate = LocalDateTime.now();

        when(newsDao.findByBetween2PublicationDate(firstPublicationDate, secondPublicationDate)).thenReturn(Optional.of(newsList));

        List<News> newsRetrieved = newsService.retrieveByBeetwen2PublicationDate(firstPublicationDate, secondPublicationDate);
        this.compareTwoNewsList(newsRetrieved, newsList);
    }


    @Test
    public void retrieveByCategoryTest(
            @Autowired @Qualifier("category-instance") Category category,
            @Autowired @Qualifier("news-list-instance") List<News> newsList) {

        when(categoryDao.findByCategoryCode(this.STR_SAMPLE))
                .thenReturn(Optional.of(category));

        List<News> newsRetrieved = newsService.retrieveByCategory(this.STR_SAMPLE);
        this.compareTwoNewsList(newsRetrieved, newsList);
    }


    @Test
    public void retrieveByNewsCodeTest(@Autowired @Qualifier("news-instance") News news) {
        when(newsDao.findNewsByNewsCode(this.STR_SAMPLE))
                .thenReturn(Optional.of(news));

        News newsRetrieved = newsService.retrieveByNewsCode(this.STR_SAMPLE);
        this.compareTwoNews(newsRetrieved, news);
    }

    @Test
    public void retrieveLast15NewsTest(@Autowired @Qualifier("news-list-instance") List<News> newsList) {
        when(newsDao.findLast15News())
                .thenReturn(Optional.of(newsList));

        List<News> newsRetrieved = this.newsService.retrieveLast15News();

        this.compareTwoNewsList(newsRetrieved, newsList);
    }

    @Test
    public void retByPosVtEqMajTest(@Autowired @Qualifier("news-list-instance") List<News> newsList) {
        when(newsDao.findByPositiveVtEqMaj(this.NUM_SAMPLE))
                .thenReturn(Optional.of(newsList));

        List<News> newsRetrieved = this.newsService.retByPosVtEqMaj(this.NUM_SAMPLE);

        this.compareTwoNewsList(newsRetrieved,newsList);
    }


    private void compareTwoNews(News newsRetrieved, News newsParam) {
        assert newsRetrieved.getNewsCode() == newsParam.getNewsCode();
        assert newsRetrieved.getSummary() == newsParam.getSummary();
        assert newsRetrieved.getTitle() == newsParam.getTitle();
        assert newsRetrieved.getPositiveVote() == newsParam.getPositiveVote();
        assert newsRetrieved.getNegativeVote() == newsParam.getNegativeVote();
    }


    private void compareTwoNewsList(List<News> outputNewsList, List<News> newsListParam) {
        assert outputNewsList.get(0).getNewsCode() == newsListParam.get(0).getNewsCode();
        assert outputNewsList.get(0).getTitle() == newsListParam.get(0).getTitle();
        assert outputNewsList.get(0).getSummary() == newsListParam.get(0).getSummary();
        assert outputNewsList.get(0).getPositiveVote() == newsListParam.get(0).getPositiveVote();
        assert outputNewsList.get(0).getNegativeVote() == newsListParam.get(0).getNegativeVote();

        assert outputNewsList.get(1).getNewsCode() == newsListParam.get(1).getNewsCode();
        assert outputNewsList.get(1).getTitle() == newsListParam.get(1).getTitle();
        assert outputNewsList.get(1).getSummary() == newsListParam.get(1).getSummary();
        assert outputNewsList.get(1).getPositiveVote() == newsListParam.get(1).getPositiveVote();
        assert outputNewsList.get(1).getNegativeVote() == newsListParam.get(1).getNegativeVote();

        assert outputNewsList.get(2).getNewsCode() == newsListParam.get(2).getNewsCode();
        assert outputNewsList.get(2).getTitle() == newsListParam.get(2).getTitle();
        assert outputNewsList.get(2).getSummary() == newsListParam.get(2).getSummary();
        assert outputNewsList.get(2).getPositiveVote() == newsListParam.get(2).getPositiveVote();
        assert outputNewsList.get(2).getNegativeVote() == newsListParam.get(2).getNegativeVote();
    }
}


