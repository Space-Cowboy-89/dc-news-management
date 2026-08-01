package com.spacecowboy89.dc.newsmanagement.unittest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacecowboy89.dc.newsmanagement.utility.constant.UserCtrlConstants;
import com.spacecowboy89.dc.newsmanagement.dto.UserDto;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import com.spacecowboy89.dc.newsmanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
    private final String CODE_20_CHARACT;


    private MockMvc mockMvc;
    @MockitoBean
    private UserService userSrv;

    private ObjectMapper objectMapper;

    @Autowired
    public UserControllerTest(MockMvc mockMvc, UserService userSrv, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.userSrv = userSrv;
        this.objectMapper = objectMapper;
        this.CODE_20_CHARACT = "xxxxxxxxxxxxxxxxxxxx";
    }

    //######################## existUserByUserCode() #############################

    @Test
    public void existUserByUserCode_200() throws Exception {
        when(userSrv.existUserByUserCode(CODE_20_CHARACT)).thenReturn(true);

        mockMvc.perform(get("/api/v1/users/exists/{userCode}", CODE_20_CHARACT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }


    @Test
    public void existUserByUserCode_400() throws Exception {
        when(userSrv.existUserByUserCode("ciaopciaopciaopciaop")).thenReturn(true);

        mockMvc.perform(get("/api/v1/users/exists/{userCode}", "xxx"))
                .andExpect(status().isBadRequest());
    }


//######################## getUsersDeleted() #############################

    @Test
    public void getUsersIsDeleted(@Autowired @Qualifier("users-instance") List<User> usersIsDeleted) throws Exception {
        when(userSrv.retrieveUserDeleted())
                .thenReturn(usersIsDeleted);

        mockMvc.perform(get("/api/v1/users/deleted"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(UserCtrlConstants.NAME_SAMPLE_1))
                .andExpect(jsonPath("$[1].username").value(UserCtrlConstants.USERNAME_SAMPLE_2))
                .andExpect(jsonPath("$[2].surname").value(UserCtrlConstants.SURNAME_SAMPLE_3));
    }


//######################## addUser() #############################

    @Test
    public void addUser(@Autowired @Qualifier("user-instance") User userCreated) throws Exception {
        when(userSrv.saveUser(any(User.class)))
                .thenReturn(userCreated);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value((UserCtrlConstants.NAME_SAMPLE_1)))
                .andExpect(jsonPath("$.surname").value((UserCtrlConstants.SURNAME_SAMPLE_1)));
    }
}