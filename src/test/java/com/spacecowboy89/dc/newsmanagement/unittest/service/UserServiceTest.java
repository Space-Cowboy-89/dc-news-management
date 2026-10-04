package com.spacecowboy89.dc.newsmanagement.unittest.service;


import com.spacecowboy89.dc.newsmanagement.persistence.dao.UserDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import com.spacecowboy89.dc.newsmanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserServiceTest {

    @MockitoBean
    private UserDao userDao;

    private final UserService userService;
    private final String STR_SAMPLE = "STR";

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }


    @Test
    public void retrieveUserByUserCodeTest(@Autowired @Qualifier("user-instance") User user) {
        when(userDao.findUserByUserCode(STR_SAMPLE)).thenReturn(Optional.of(user));

        User userRetrieved = this.userService.retrieveUserByUserCode(STR_SAMPLE);

        assert userRetrieved.getSurname() == user.getSurname();
        assert userRetrieved.getUserCode() == user.getUserCode();
    }

    @Test
    public void saveUserTest(@Autowired @Qualifier("user-instance") User user) {
        when(userDao.persistUser(user)).thenReturn(Optional.of(user));

        User userSaved = userService.saveUser(user);

        assert userSaved.getUserCode() == user.getUserCode();
        assert userSaved.getName() == user.getName();
        assert userSaved.getSurname() == user.getSurname();
    }

    @Test
    public void existUserByUserCodeTest() {
        when(userDao.existByUserCode(this.STR_SAMPLE)).thenReturn(Optional.of(true));

        boolean isExist = userService.existUserByUserCode(this.STR_SAMPLE);

        assert isExist == true;
    }

    @Test
    public void retrieveUserDeletedTest(@Autowired @Qualifier("users-instance") List<User> users) {
        when(userDao.findByDeletedAtIsNotNull()).thenReturn(Optional.of(users));

        List<User> usersDeleted = userService.retrieveUserDeleted();

        assert usersDeleted.get(0).getName() == users.get(0).getName();
        assert usersDeleted.get(1).getSurname() == users.get(1).getSurname();
        assert usersDeleted.get(2).getUserCode() == users.get(2).getUserCode();
    }

    @Test
    public void retrieveByIdTest(@Autowired @Qualifier("user-instance") User user) {
        when(userDao.findById(1l)).thenReturn(Optional.of(user));

        User userRetrieved = userService.retrieveById(1l);

        assert userRetrieved.getUserCode() == user.getUserCode();
        assert userRetrieved.getName() == user.getName();
        assert userRetrieved.getUsername() == user.getUsername();
    }
}
