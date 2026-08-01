package com.spacecowboy89.dc.newsmanagement.integration.dao;

import com.spacecowboy89.dc.newsmanagement.utility.constant.NewsCtrlConstants;
import com.spacecowboy89.dc.newsmanagement.utility.factory.CtrlTestFactory;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.CategoryDao;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.JournalistDao;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.NewsDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Category;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Journalist;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DataJpaTest
@ActiveProfiles("test")
@Import({JournalistDao.class, CategoryDao.class, NewsDao.class, CtrlTestFactory.class})
public class NewsDaoTest {

    private NewsDao newsDao;

    private Journalist journalist;
    private Category category;

    @Autowired
    public NewsDaoTest(NewsDao newsDao, JournalistDao journalistDao, CategoryDao categoryDao,
                       @Autowired @Qualifier("category-instance") Category category,
                       @Autowired @Qualifier("journalist-instance") Journalist journalist) {
        this.newsDao = newsDao;
        this.category = categoryDao.persist(category).get();
        this.journalist = journalistDao.persist(journalist).get();
    }

    @Test
    public void findNewsByNewsCodeTest(@Autowired @Qualifier("news-instance") News newsParam) {
        this.persistAndTestNews(newsParam);
    }

    @Test
    public void persistTest(@Autowired @Qualifier("news-instance") News newsParam) {
         this.persistAndTestNews(newsParam);
    }

    private void persistAndTestNews(News newsParam) {

        newsParam.setCategory(this.category);
        newsParam.setJournalist(this.journalist);
        News newsPersisted = newsDao.persist(newsParam).get();

        News newsFinded = newsDao.findNewsByNewsCode(newsPersisted.getNewsCode()).get();

        assert newsFinded.getTitle() == newsPersisted.getTitle();
        assert newsFinded.getSummary() == newsPersisted.getSummary();

        newsParam.setId(null);
    }


    @Test
    public void findBetween2PublicationDateTest(@Autowired @Qualifier("news-instance") News newsParam) {
        newsParam.setJournalist(this.journalist);
        newsParam.setCategory(this.category);

        News firstNewsPersisted = newsDao.persist(newsParam).get();

        News othNews = this.createNewsInstance(NewsCtrlConstants.NEWSCODE_SAMPLE_2);
        News secondNewsPersisted = newsDao.persist(othNews).get();

        List<News> newsFinded = newsDao.findByBetween2PublicationDate(
                        firstNewsPersisted.getPublicationDate(),
                        secondNewsPersisted.getPublicationDate().plusDays(1))
                .get();

        assert firstNewsPersisted.getNewsCode() == newsFinded.get(0).getNewsCode();
        assert firstNewsPersisted.getTitle() == newsFinded.get(0).getTitle();

        assert secondNewsPersisted.getNewsCode() == newsFinded.get(1).getNewsCode();
        assert secondNewsPersisted.getTitle() == newsFinded.get(1).getTitle();

        newsParam.setId(null);
    }

    @Test
    public void findByPositiveVtEqMajTest(@Autowired @Qualifier("news-instance") News news) {
        news.setJournalist(this.journalist);
        news.setCategory(this.category);
        news.setPositiveVote(3);
        News firstNewsPersisted = newsDao.persist(news).get();

        News secondNews = this.createNewsInstance(NewsCtrlConstants.NEWSCODE_SAMPLE_2);
        secondNews.setPositiveVote(5);
        News secondNewsPersisted = newsDao.persist(secondNews).get();

        List<News> newsFinded = newsDao.findByPositiveVtEqMaj(2).get();

        assert firstNewsPersisted.getNewsCode() == newsFinded.get(0).getNewsCode();
        assert firstNewsPersisted.getTitle() == newsFinded.get(0).getTitle();

        assert secondNewsPersisted.getNewsCode() == newsFinded.get(1).getNewsCode();
        assert secondNewsPersisted.getTitle() == newsFinded.get(1).getTitle();

        news.setId(null);
    }

    @Test
    public void persistAllTest() {
        this.persistAndTestNewsList();
    }


    @Test
    public void findLast15NewsTest() {
        this.persistAndTestNewsList();
    }


    private void persistAndTestNewsList() {
        List<News> newsToPersist = new ArrayList<>();

        for (int i = 0; i < 15; ++i) {
            newsToPersist.add(createNewsInstance(
                    UUID.randomUUID()
                            .toString()
                            .replace("-", "")
                            .substring(0, 20)
            ));
        }

        List<News> newsPersisted = newsDao.persistAll(newsToPersist).get();

        assert newsToPersist.size() == newsPersisted.size();
        assert newsToPersist.get(0).getNewsCode() == newsPersisted.get(0).getNewsCode();
        assert newsToPersist.get(1).getNewsCode() == newsPersisted.get(1).getNewsCode();
   }

    private News createNewsInstance(String newsCode) {
        News news = new News(newsCode,
                NewsCtrlConstants.TITLE_SAMPLE_2,
                NewsCtrlConstants.SUMMARY_SAMPLE_2,
                NewsCtrlConstants.PUBLICATION_DATE_2);
        news.setCategory(this.category);
        news.setJournalist(this.journalist);
        return news;
    }
}
