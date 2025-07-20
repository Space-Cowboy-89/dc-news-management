package com.spacecowboy89.dc.newsmanagement.web;

import com.spacecowboy89.dc.newsmanagement.dto.MainInfoNewsDto;
import com.spacecowboy89.dc.newsmanagement.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("news")
@Slf4j
@Tag(name = "News", description = "Operation about news.")
public class NewsController {
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @Operation(
            summary ="",
            description= "",)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "retrieve 15 news's main info"),
            @ApiResponse(responseCode = "404", description = "retrieve 15 news's main info"),
            @ApiResponse(responseCode = "400", description = "retrieve 15 news's main info")

    })
    @GetMapping(value = "lastMainInfoNews")
    public List<MainInfoNewsDto> getLastMainInfoNews(){
        log.info("Endpoint 'lastMainInfoNews' started.");
        return newsService.retrieveLast15MainInfoNews();
    }

}

