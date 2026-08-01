package com.spacecowboy89.dc.newsmanagement.integration.dao;


import com.spacecowboy89.dc.newsmanagement.utility.factory.CtrlTestFactory;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.UserDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;


@DataJpaTest
@ActiveProfiles("test")
@Import({UserDao.class, CtrlTestFactory.class})
public class UserDaoTest {

    private UserDao userDao;

    @Autowired
    public UserDaoTest(UserDao userDao) {
        this.userDao = userDao;
    }

    @Test
    public void persistTest(@Autowired @Qualifier("user-instance") User user) {
        User userPersisted = userDao.persistUser(user).get();

        User userFinded = userDao.findUserByUserCode(userPersisted.getUserCode()).get();

        assert userPersisted.getName() == userFinded.getName();
        assert userPersisted.getSurname() == userFinded.getSurname();
        assert userPersisted.getEmail() == userFinded.getEmail();

        user.setId(null);
    }

    @Test
    public void findUserByUserCodeTest(@Autowired @Qualifier("user-instance") User user) {
        User userPersisted = userDao.persistUser(user).get();

        User userFinded = userDao.findUserByUserCode(userPersisted.getUserCode()).get();

        assert userPersisted.getName() == userFinded.getName();
        assert userPersisted.getSurname() == userFinded.getSurname();
        assert userPersisted.getEmail() == userFinded.getEmail();

        user.setId(null);
    }

    @Test
    public void existByUserCodeTest(@Autowired @Qualifier("user-instance") User user) {
        userDao.persistUser(user);

        boolean isExists = userDao.existByUserCode(user.getUserCode()).get();

        assert isExists == true;

        user.setId(null);
    }

    @Test
    public void findByDeletedAtIsNotNullTest(@Autowired @Qualifier("user-instance") User userParam) {
        userParam.setDeletedAt(LocalDateTime.now());
        User userPersisted = userDao.persistUser(userParam).get();

        User userFinded = userDao.findByDeletedAtIsNotNull().get().get(0);

        assert userPersisted.getUserCode() == userFinded.getUserCode();
        assert userPersisted.getName() == userFinded.getName();
        assert userPersisted.getSurname() == userFinded.getSurname();

        userParam.setId(null);
    }


}
