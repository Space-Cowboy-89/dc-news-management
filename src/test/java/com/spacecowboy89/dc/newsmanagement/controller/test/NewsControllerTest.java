package com.spacecowboy89.dc.newsmanagement.controller.test;


import com.spacecowboy89.dc.newsmanagement.controller.constant.NewsCtrlConstants;
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

    private final MockMvc mockMvc;
    @MockitoBean
    private final NewsService newsService;

    @Autowired
    public NewsControllerTest(MockMvc mockMvc, NewsService newsService) {
        this.mockMvc = mockMvc;
        this.newsService = newsService;
    }


    @Test
    public void newsByNewsCode200(@Autowired @Qualifier("news-instance") News news) throws Exception {
        when(newsService.retrieveByNewsCode(any(String.class)))
                .thenReturn(news);

        mockMvc.perform(get("/news")
                        .param("news-code", "qazxsqazxsqazxsqazxs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(NewsCtrlConstants.TITLE_SAMPLE_1))
                .andExpect(jsonPath("$.newsCode").value(NewsCtrlConstants.NEWSCODE_SAMPLE_1))
                .andExpect(jsonPath("$.summary").value(NewsCtrlConstants.SUMMARY_SAMPLE_1));
    }

    @Test
    public void last15News(@Autowired @Qualifier("news-list-instance") List<News> newsList) throws Exception {
        when(newsService.retrieveLast15News())
                .thenReturn(newsList);

        mockMvc.perform(get("/news/last-15-news"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(NewsCtrlConstants.TITLE_SAMPLE_1))
                .andExpect(jsonPath("$[0].newsCode").value(NewsCtrlConstants.NEWSCODE_SAMPLE_1))
                .andExpect(jsonPath("$[1].title").value(NewsCtrlConstants.TITLE_SAMPLE_2))
                .andExpect(jsonPath("$[1].newsCode").value(NewsCtrlConstants.NEWSCODE_SAMPLE_2))
                .andExpect(jsonPath("$[2].title").value(NewsCtrlConstants.TITLE_SAMPLE_3))
                .andExpect(jsonPath("$[2].newsCode").value(NewsCtrlConstants.NEWSCODE_SAMPLE_3));
    }
}