package com.spacecowboy89.dc.newsmanagement.unittest.controller;


import com.spacecowboy89.dc.newsmanagement.utility.constant.NewsCtrlConstants;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import com.spacecowboy89.dc.newsmanagement.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NewsControllerTest {
    private final LocalDateTime NOWTM;
    private final LocalDateTime NOWTMPLUS5;
    private final String CODE_20_CHARACT;

    private final MockMvc mockMvc;
    @MockitoBean
    private final NewsService newsService;

    @Autowired
    public NewsControllerTest(MockMvc mockMvc, NewsService newsService) {
        this.mockMvc = mockMvc;
        this.newsService = newsService;
        this.NOWTM = LocalDateTime.now();
        this.NOWTMPLUS5 = this.NOWTM.plusDays(5L);
        this.CODE_20_CHARACT= "xxxxxxxxxxxxxxxxxxxx";
    }


    @Test
    public void newsByNewsCode(@Autowired @Qualifier("news-instance") News news) throws Exception {
        when(newsService.retrieveByNewsCode(any(String.class)))
                .thenReturn(news);

        ResultActions resultActions = mockMvc.perform(
                        get("/api/v1/news/{newsCode}",CODE_20_CHARACT))
                .andExpect(status().isOk());

        this.checkSingleJsonResponse(resultActions);
    }

    @Test
    public void last15News(@Autowired @Qualifier("news-list-instance") List<News> newsList) throws Exception {
        when(newsService.retrieveLast15News())
                .thenReturn(newsList);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/news/last-15"))
                .andExpect(status().isOk());

        this.checkJsonResponse(resultActions);
    }


    @Test
    public void getLastMainInfoNews(@Autowired @Qualifier("news-list-instance") List<News> newsList) throws Exception {
        when(newsService.retrieveLast15News()).
                thenReturn(newsList);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/news/last-15-main-Info"))
                .andExpect(status().isOk());

        this.checkJsonResponse(resultActions);
    }

    @Test
    public void getNewsByCategory(@Autowired @Qualifier("news-list-instance") List<News> newsList) throws Exception {
        when(newsService.retrieveByCategory(any(String.class)))
                .thenReturn(newsList);

        ResultActions resultActions = mockMvc.perform(
                        get("/api/v1/news/category/{categoryCode}",CODE_20_CHARACT))
                .andExpect(status().isOk());

        this.checkJsonResponse(resultActions);
    }



    @Test
    public void between2PublicationDate(@Autowired @Qualifier("news-list-instance") List<News> newsList) throws Exception {
        when(newsService.retrieveByBeetwen2PublicationDate(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(newsList);

        ResultActions resultActions = mockMvc.perform(
                        get("/api/v1/news/between2PublicationDate/{firstPublicationDate}/{secondPublicationDate}",this.NOWTM,this.NOWTM)
                                .param("first-publication-date", NOWTM.toString())
                                .param("second-publication-date", NOWTMPLUS5.toString()))
                .andExpect(status().isOk());

        this.checkJsonResponse(resultActions);
    }

    @Test
    public void getByPosVtEqMajTest(@Autowired @Qualifier("news-list-instance") List<News> newsList) throws Exception {
        when(newsService.retByPosVtEqMaj(any(Integer.class)))
                .thenReturn(newsList);

        ResultActions resultActions =
                mockMvc.perform(get("/api/v1/news/posVoteEqualMajor/{minorBound}","5"))
                        .andExpect(status().isOk());

        this.checkJsonResponse(resultActions);
    }


    @Test
    public void getByJournalistTest(@Autowired @Qualifier("news-list-instance") List<News> newsList) throws Exception {
        when(newsService.retrieveByJournalist(any(String.class)))
                .thenReturn(newsList);

        ResultActions resultActions =
                mockMvc.perform(get("/api/v1/news/journalist/{journalistCode}", CODE_20_CHARACT))
                        .andExpect(status().isOk());

        this.checkJsonResponse(resultActions);
    }

    private void checkSingleJsonResponse(ResultActions resultActions) throws Exception {
        resultActions
                .andExpect(jsonPath("$.title").value(NewsCtrlConstants.TITLE_SAMPLE_1))
                .andExpect(jsonPath("$.summary").value(NewsCtrlConstants.SUMMARY_SAMPLE_1))
                .andExpect(jsonPath("$.newsCode").value(NewsCtrlConstants.NEWSCODE_SAMPLE_1));
    }

    private void checkJsonResponse(ResultActions resultActions) throws Exception {
        resultActions
                .andExpect(jsonPath("$[0].title").value(NewsCtrlConstants.TITLE_SAMPLE_1))
                .andExpect(jsonPath("$[0].summary").value(NewsCtrlConstants.SUMMARY_SAMPLE_1))
                .andExpect(jsonPath("$[1].title").value(NewsCtrlConstants.TITLE_SAMPLE_2))
                .andExpect(jsonPath("$[1].summary").value(NewsCtrlConstants.SUMMARY_SAMPLE_2))
                .andExpect(jsonPath("$[2].title").value(NewsCtrlConstants.TITLE_SAMPLE_3))
                .andExpect(jsonPath("$[2].summary").value(NewsCtrlConstants.SUMMARY_SAMPLE_3));
    }
}