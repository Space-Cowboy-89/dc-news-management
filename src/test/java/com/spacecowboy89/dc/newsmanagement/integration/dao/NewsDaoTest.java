package com.spacecowboy89.dc.newsmanagement.integration.dao;

import com.spacecowboy89.dc.newsmanagement.controller.constant.NewsCtrlConstants;
import com.spacecowboy89.dc.newsmanagement.controller.factory.CtrlTestFactory;
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

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@Import({NewsDao.class, CtrlTestFactory.class})
public class NewsDaoTest {

    private NewsDao newsDao;

    @Autowired
    public NewsDaoTest(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Test
    public void findNewsByNewsCodeTest(@Autowired @Qualifier("news-instance") News newsParam) {
        newsParam.setJournalist(new Journalist());
        newsParam.setCategory(new Category());
        News newsPersisted = newsDao.persist(newsParam).get();


        News newsFinded = newsDao.findNewsByNewsCode(newsPersisted.getNewsCode()).get();

        assert newsFinded.getTitle() == newsPersisted.getTitle();
        assert newsFinded.getSummary() == newsPersisted.getSummary();
    }

    @Test
    public void persistTest(@Autowired @Qualifier("news-instance") News newsParam) {
        News newsPersisted = newsDao.persist(newsParam).get();

        News newsFinded = newsDao.findNewsByNewsCode(newsPersisted.getNewsCode()).get();

        assert newsFinded.getTitle() == newsPersisted.getTitle();
        assert newsFinded.getSummary() == newsPersisted.getSummary();
    }


    @Test
    public void findBetween2PublicationDateTest(@Autowired @Qualifier("news-instance") News newsParam){
        News othNews = new News(NewsCtrlConstants.NEWSCODE_SAMPLE_2,
                NewsCtrlConstants.TITLE_SAMPLE_2,
                NewsCtrlConstants.SUMMARY_SAMPLE_2,
                NewsCtrlConstants.PUBLICATION_DATE_2);

        News firstNewsPersisted = newsDao.persist(newsParam).get();
        News secondNewsPersisted = newsDao.persist(othNews).get();

        List<News> newsFinded = newsDao.findByBetween2PublicationDate(firstNewsPersisted.getPublicationDate(),
                secondNewsPersisted.getPublicationDate())
                .get();

        assert firstNewsPersisted.getNewsCode() == newsFinded.get(0).getNewsCode();
        assert firstNewsPersisted.getTitle() == newsFinded.get(0).getTitle();

        assert secondNewsPersisted.getNewsCode() == newsFinded.get(1).getNewsCode();
        assert secondNewsPersisted.getTitle() == newsFinded.get(1).getTitle();
    }
}
