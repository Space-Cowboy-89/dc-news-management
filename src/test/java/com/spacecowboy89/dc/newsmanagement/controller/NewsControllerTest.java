package com.spacecowboy89.dc.newsmanagement.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NewsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public NewsControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


    @Test
    public void ciaoMondo() throws Exception{


    }



}