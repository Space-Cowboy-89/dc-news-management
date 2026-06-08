package com.spacecowboy89.dc.newsmanagement.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacecowboy89.dc.newsmanagement.dto.NewsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NewsControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public NewsControllerTest(MockMvc mockMvc,ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }


    @Test
    public void newsByNewsCode200() throws Exception{
        String jsonResponse = mockMvc.perform(get("/news")
                .param("news-code","qazxsqazxsqazxsqazxs"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        NewsDto newsDtoActual = objectMapper.readValue(
                jsonResponse,
                new TypeReference<NewsDto>() {}
        );
    }



}