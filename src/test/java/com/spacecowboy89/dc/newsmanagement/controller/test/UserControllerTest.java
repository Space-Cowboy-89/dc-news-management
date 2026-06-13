package com.spacecowboy89.dc.newsmanagement.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacecowboy89.dc.newsmanagement.dto.UserDto;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import com.spacecowboy89.dc.newsmanagement.service.UserService;
import com.spacecowboy89.dc.newsmanagement.web.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private UserService userSrv;
    @Autowired
    private ObjectMapper objectMapper;


//######################## existUserByUserCode() #############################

    @Test
    public void existUserByUserCode_200() throws Exception {
        when(userSrv.existUserByUserCode("ciaopciaopciaopciaop")).thenReturn(true);

        mockMvc.perform(get("/user/exists")
                .param("user-code", "ciaopciaopciaopciaop"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }


    @Test
    public void existUserByUserCode_400() throws Exception {
        when(userSrv.existUserByUserCode("ciaopciaopciaopciaop")).thenReturn(true);

        mockMvc.perform(get("/user/exists")
                        .param("user-code", "ciao"))
                .andExpect(status().isBadRequest());
    }


//######################## getUsersDeleted() #############################

    @Test
    public void getUsersDeleted_200() throws Exception{

        when(userSrv.retrieveUserDeleted())
                .thenReturn(this.getUserList());

        mockMvc.perform(get("/user/usersDeleted"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(equalToIgnoringCase("Mario")))
                .andExpect(jsonPath("$[1].username").value(equalToIgnoringCase("water")))
                .andExpect(jsonPath("$[2].surname").value(equalToIgnoringCase("Neri")));
    }


//######################## addUser() #############################

    @Test
    public void addUser_200() throws Exception{
        User userOutput = new User("Paolo","Neri","dasap",
                "stone","roses");

        when(userSrv.saveUser(any(User.class)))
                .thenReturn(userOutput);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(equalToIgnoringCase("Paolo")))
                .andExpect(jsonPath("$.surname").value(equalToIgnoringCase("Neri")));
    }


    @Test
    public void addUser_400() throws Exception{
        when(userSrv.saveUser(any(User.class)))
                .thenReturn(new User());

        mockMvc.perform(post("/user")
                        .param("param","hello"))
                .andExpect(status().isBadRequest());
    }


//######################## Other methods #############################

    private List<User> getUserList(){
        return List.of(
                new User("Mario","Rossi", "mlmlmsa",
                        "fire","lato"),
                new User("Franco","Verdi", ".ò.",
                        "water","caldo"),
                new User("Alex","Neri", ".àò.mlmlmsa",
                        "wind","caspita")
        );
    }

}
