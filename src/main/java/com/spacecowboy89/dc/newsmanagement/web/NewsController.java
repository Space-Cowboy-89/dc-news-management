package com.spacecowboy89.dc.newsmanagement.web;

import com.spacecowboy89.dc.newsmanagement.dto.NewsDto;
import com.spacecowboy89.dc.newsmanagement.dto.NewsInfoDto;
import com.spacecowboy89.dc.newsmanagement.service.NewsService;
import com.spacecowboy89.dc.newsmanagement.utility.mapper.NewsMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/news")
@Slf4j
@Validated
@Tag(name = "News", description = "These endpoints work with news!")
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @Operation(
            summary = "Retrieve main information of last 15 news.",
            description = "Retrieve main information of last 15 news.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve main information of last 15 news succesfully."),
            @ApiResponse(responseCode = "400", description = "Error 400."),
            @ApiResponse(responseCode = "404", description = "News doesn't find.")
    })
    @GetMapping(value = "/lastMainInfoNews")
    public ResponseEntity<List<NewsInfoDto>> getLastMainInfoNews() {
        log.info("Endpoint 'lastMainInfoNews' called.");

        List<NewsInfoDto> newsInfoDtoList = NewsMapper.INSTANCE.toNewsInfoDtoList(
                newsService.retrieveLast15NewsInfo());
        return ResponseEntity
                .ok()
                .header("Header", "Retrieve main info news!")
                .body(newsInfoDtoList);
    }


    @Operation(
            summary = "Retrieve news by a specific newsCode.",
            description = "Retrieve news by a specific newsCode.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "News retrieves successfuly"),
            @ApiResponse(responseCode = "400", description = ""),
            @ApiResponse(responseCode = "404", description = " News doesnt' present.")
    })
    @GetMapping(value = "/newsByNewsCode")
    public ResponseEntity<NewsDto> getNewsByNewsCode(@RequestParam @NotBlank @Size(min = 20, max = 20) String newsCode) {
        log.info("Endpoint 'getNewsByNewsCode' called.");

        NewsDto newsDto = NewsMapper.INSTANCE.toNewsDto(newsService.retrieveByNewsCode(newsCode));

        return ResponseEntity
                .ok()
                .header("Header", "Retrieve news By newsCode!")
                .body(newsDto);
    }


    @Operation(
            summary = "Retrevies last 15 news.",
            description = "Retrevies last 15 news.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve last 15 news successfuly"),
            @ApiResponse(responseCode = "400", description = ""),
            @ApiResponse(responseCode = "404", description = "last 15 news doesn't found!")
    })
    @GetMapping("/last15News")
    public ResponseEntity<List<NewsDto>> getLast15News() {

        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(newsService.retrieveLast15Info());
        return ResponseEntity
                .ok()
                .header("Header", "Retrieve last 15 news!")
                .body(newsDtoList);
    }


    @Operation(
            summary = "Retrevies news by categorylast 15 news.",
            description = "Retrevies news by categorylast 15 news.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve last 15 news successfuly"),
            @ApiResponse(responseCode = "400", description = ""),
            @ApiResponse(responseCode = "404", description = "last 15 news doesn't found!")
    })
    @GetMapping("/newsByCategory")
    public ResponseEntity<List<NewsDto>> getNewsByCategory(@RequestParam @NotBlank @Size(min = 20, max = 20) String categoryCode) {
        log.info("getNewsByCategory in Execution!");

        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(newsService.retrieveByCategory(categoryCode));
        return ResponseEntity
                .ok()
                .header("")
                .body(newsDtoList);
    }


    @Operation(
            summary = "Retrevies news between two date!.",
            description = "Retrevies news between two date!")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve news between two date with success!"),
            @ApiResponse(responseCode = "400", description = ""),
            @ApiResponse(responseCode = "404", description = "last 15 news doesn't found!")
    })
    @GetMapping("/beetwenTwoPublicationDate")
    public ResponseEntity<List<NewsDto>> between2PublicationDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime firstPublicationDate,
                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime secondPublicationDate) {
        log.info("beetwen2PublicationDate in execution!");


        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(newsService.retrieveByBeetwen2PublicationDate(firstPublicationDate, secondPublicationDate));
        return ResponseEntity
                .ok()
                .header("Header!", "beetwen2PublicationDate")
                .body(newsDtoList);
    }


    @Operation(
            summary = "Retrevies news by a journalist!.",
            description = "Retrevies news by a journalist. He's rappresented by journalist_code!")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve news by a journalist with success!"),
            @ApiResponse(responseCode = "400", description = ""),
            @ApiResponse(responseCode = "404", description = "There aren't news of this journalist!")
    })
    @GetMapping("/byJournalist")
    public ResponseEntity<List<NewsDto>> getByJournalist(@RequestParam @NotBlank @Size(min = 20, max = 30) String journalistCode) {
        log.info("getByJournalist in execution!");

        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(
                newsService.retrieveByJournalist(journalistCode));

        return ResponseEntity
                .ok()
                .header("Header", "an header!")
                .body(newsDtoList);
    }

}

