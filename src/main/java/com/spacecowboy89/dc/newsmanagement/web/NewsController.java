package com.spacecowboy89.dc.newsmanagement.web;

import com.spacecowboy89.dc.newsmanagement.dto.ErrorResponse;
import com.spacecowboy89.dc.newsmanagement.dto.NewsDto;
import com.spacecowboy89.dc.newsmanagement.dto.NewsInfoDto;
import com.spacecowboy89.dc.newsmanagement.service.NewsService;
import com.spacecowboy89.dc.newsmanagement.utility.mapper.NewsMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
            @ApiResponse(
                    responseCode = "200",
                    description = "Retrieve main information of last 15 news succesfully."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Input not valid",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "News not found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Software internal error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping(value = "/last-15-main-Info-News")
    public ResponseEntity<List<NewsInfoDto>> getLastMainInfoNews() {
        log.info("lastMainInfoNews endpoint in execution!");

        List<NewsInfoDto> newsInfoDtoList = NewsMapper.INSTANCE.toNewsInfoDtoList(
                newsService.retrieveLast15NewsInfo());

        log.info("lastMainInfoNews endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "Retrieve main info news!")
                .body(newsInfoDtoList);
    }


    @Operation(
            summary = "It Retrieves a specific news.",
            description = "It Retrieves news by a specific newsCode.",
            parameters = @Parameter(name = "newsCode", description = "It's a univoque code value of a news.")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "News retrieved with success."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Inputs are not valid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = " News doesn't found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Software internal error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping(params = "news-code")
    public ResponseEntity<NewsDto> getNewsByNewsCode(@RequestParam(value= "news-code") @NotBlank @Size(min = 20, max = 20) String newsCode) {
        log.info("getNewsByNewsCode endpoint in execution!");

        NewsDto newsDto = NewsMapper.INSTANCE.toNewsDto(newsService.retrieveByNewsCode(newsCode));

        log.info("getNewsByNewsCode endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "Retrieve news By newsCode!")
                .body(newsDto);
    }


    @Operation(
            summary = "Retrevies last 15 news.",
            description = "Retrevies last 15 news.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Retrieve last 15 news successfuly"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Something went wrong.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "last 15 news doesn't found!",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Software internal error!",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/last-15-news")
    public ResponseEntity<List<NewsDto>> getLast15News() {
        log.info("getLast15News endpoint in execution!");
        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(newsService.retrieveLast15Info());

        log.info("getLast15News endpoint executed successfully!");
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
    @GetMapping(params="category-code")
    public ResponseEntity<List<NewsDto>> getNewsByCategory(@RequestParam("category-code") @NotBlank @Size(min = 20, max = 20) String categoryCode) {
        log.info("getNewsByCategory endpoint in execution!");

        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(newsService.retrieveByCategory(categoryCode));

        log.info("getNewsByCategory endpoint executed successfully!");
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
    @GetMapping(params = {"first-publication-date","second-publication-date"})
    public ResponseEntity<List<NewsDto>> between2PublicationDate(@RequestParam(value = "first-publication-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime firstPublicationDate,
                                                                 @RequestParam(value = "second-publication-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime secondPublicationDate) {
        log.info("between2PublicationDate endpoint in execution!");

        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(newsService.retrieveByBeetwen2PublicationDate(firstPublicationDate, secondPublicationDate));

        log.info("between2PublicationDate endpoint executed successfully!");
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
    @GetMapping(params ="journalist-code")
    public ResponseEntity<List<NewsDto>> getByJournalist(@RequestParam(value = "journalist-code") @NotBlank @Size(min = 20, max = 30) String journalistCode) {
        log.info("getByJournalist endpoint in execution!");

        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(
                newsService.retrieveByJournalist(journalistCode));

        log.info("getByJournalist endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "an header!")
                .body(newsDtoList);
    }
}

