package com.spacecowboy89.dc.newsmanagement.web;

import com.spacecowboy89.dc.newsmanagement.dto.NewsDto;
import com.spacecowboy89.dc.newsmanagement.dto.NewsInfoDto;
import com.spacecowboy89.dc.newsmanagement.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
@Slf4j
@Tag(name = "News", description = "These endpoints work with news!")
public class NewsController {
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Operation(
            summary = "Retrieve main information of last 15 news.",
            description="Retrieve main information of last 15 news.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve main information of last 15 news succesfully."),
            @ApiResponse(responseCode = "404", description = "News doesn't find."),
            @ApiResponse(responseCode = "400", description = "Error 400.")

    })
    @GetMapping(value = "/lastMainInfoNews")
    public ResponseEntity<List<NewsInfoDto>> getLastMainInfoNews(){
        log.info("Endpoint 'lastMainInfoNews' called.");
        return ResponseEntity
                .ok()
                .header("Header", "Retrieve main info news!")
                .body(newsService.retrieveLast15NewsInfo());
    }


    @Operation(
            summary = "Retrieve news by a specific newsCode.",
            description = "Retrieve news by a specific newsCode."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "News retrieves successfuly"),
            @ApiResponse(responseCode = "404", description =" News doesnt' present."),
            @ApiResponse(responseCode = "400", description = "")
    })
    @GetMapping(value = "/newsByNewsCode")
    public ResponseEntity<NewsDto> getNewsByNewsCode(@RequestParam String newsCode){
        log.info("Endpoint 'getNewsByNewsCode' called.");

        return ResponseEntity
                .ok()
                .header("Header","Retrieve news By newsCode!")
                .body(newsService.retrieveByNewsCode(newsCode));
    }



    @Operation(
            summary="Retrevies last 15 news.",
            description ="Retrevies last 15 news."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description= "Retrieve last 15 news successfuly"),
            @ApiResponse(responseCode = "404", description="last 15 news doesn't found!"),
            @ApiResponse(responseCode = "400", description="")
    })
    @GetMapping("/last15News")
    public ResponseEntity<List<NewsDto>> getLast15News(){
        return ResponseEntity
                .ok()
                .header("Header","Retrieve last 15 news!")
                .body(newsService.retrieveLast15Info());
    }
}

