package com.spacecowboy89.dc.newsmanagement.service;


import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
public class NewsServiceTest {

    private final NewsService newsService;

    public NewsServiceTest(NewsService newsService) {
        this.newsService = newsService;
    }




    public void retrieveByNewsCode (){
        String newsCode = "abcdeabcdeabcdeabcde";
        News localNews = new News(newsCode,"dsdassada", "askmdlamdl",
                LocalDateTime.of(1989,10,2,13,10,1));


        News responseNews = newsService.retrieveByNewsCode(newsCode);


    }
}
