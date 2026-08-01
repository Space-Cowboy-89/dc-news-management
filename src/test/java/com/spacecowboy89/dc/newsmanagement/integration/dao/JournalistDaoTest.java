package com.spacecowboy89.dc.newsmanagement.integration.dao;


import com.spacecowboy89.dc.newsmanagement.utility.factory.CtrlTestFactory;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.JournalistDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Journalist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@Import({JournalistDao.class, CtrlTestFactory.class})
public class JournalistDaoTest {

    private JournalistDao journalistDao;

    @Autowired
    public JournalistDaoTest(JournalistDao journalistDao) {
        this.journalistDao = journalistDao;
    }


    @Test
    public void findByIdTest(@Autowired @Qualifier("journalist-instance") Journalist journalist){
        findByIdAndPersistTest(journalist);
    }

    @Test
    public void persistTest(@Autowired @Qualifier("journalist-instance") Journalist journalist){
        findByIdAndPersistTest(journalist);
    }


    private void findByIdAndPersistTest(Journalist journalist){
        Journalist journalistPersisted = this.journalistDao.persist(journalist).get();

        Journalist journalistFinded = this.journalistDao.findById(journalistPersisted.getJournalistCode()).get();

        assert journalistFinded.getJournalistCode() == journalistPersisted.getJournalistCode();
        assert journalistFinded.getName() == journalistPersisted.getName();
        assert  journalistFinded.getSurname() == journalistPersisted.getSurname();
    }
}
