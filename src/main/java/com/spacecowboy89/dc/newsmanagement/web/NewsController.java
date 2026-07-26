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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
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
                    responseCode = "200", description = "News retrieved successfully."),
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
                    description = "Internal server error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping(value = "/last-15-main-Info")
    public ResponseEntity<List<NewsInfoDto>> getLast15MainInfo() {
        log.info("lastMainInfoNews endpoint in execution!");

        List<NewsInfoDto> newsInfoDtoList = NewsMapper.INSTANCE.toNewsInfoDtoList(
                newsService.retrieveLast15News());

        log.info("lastMainInfoNews endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "Retrieve main info news!")
                .body(newsInfoDtoList);
    }


    @Operation(
            summary = "Retrieve news by news code..",
            description = "Retrieve news by news code.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "News retrieved successfully."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Inputs are not valid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = " News not found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping("{newsCode}")
    public ResponseEntity<NewsDto> getByNewsCode(@Parameter(description = "news code", example = "xxx") @PathVariable @NotBlank @Size(min = 20, max = 20) String newsCode) {
        log.info("getNewsByNewsCode endpoint in execution!");

        NewsDto newsDto = NewsMapper.INSTANCE.toNewsDto(newsService.retrieveByNewsCode(newsCode));

        log.info("getNewsByNewsCode endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "Retrieve news By newsCode!")
                .body(newsDto);
    }


    @Operation(
            summary = "Retrieve last 15 news.",
            description = "Retrieve last 15 news.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "News retrieved successfully."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Input are not valid",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "News not found!",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error!",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/last-15")
    public ResponseEntity<List<NewsDto>> getLast15() {
        log.info("getLast15News endpoint in execution!");
        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(newsService.retrieveLast15News());

        log.info("getLast15News endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "Retrieve last 15 news!")
                .body(newsDtoList);
    }


    @Operation(
            summary = "Retrieve news by category.",
            description = "Retrieve news by category.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "News retrieved successfully."),
            @ApiResponse(responseCode = "400", description = "Input not valid."),
            @ApiResponse(responseCode = "404", description = "News not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    @GetMapping("/category/{categoryCode}")
    public ResponseEntity<List<NewsDto>> getByCategory(
            @Parameter(description = "category_code", example = "xxx")
            @PathVariable @NotBlank @Size(min = 20, max = 20) String categoryCode) {
        log.info("getNewsByCategory endpoint in execution!");

        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(newsService.retrieveByCategory(categoryCode));

        log.info("getNewsByCategory endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("")
                .body(newsDtoList);
    }


    @Operation(
            summary = "Retrieve news pubblicated between two publication date.",
            description = "Retrieve news pubblicated between two publication date.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "News retrieved successfully."),
            @ApiResponse(responseCode = "400", description = "Input not valid."),
            @ApiResponse(responseCode = "404", description = "News not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/between2PublicationDate/{firstPublicationDate}/{secondPublicationDate}")
    public ResponseEntity<List<NewsDto>> between2PublicationDate(
            @Parameter(description = "lower limit date", example = "2025-10-14 08:48:23.000") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime firstPublicationDate,
            @Parameter(description = "upper limit date.", example = "2025-10-14 08:48:23.000") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime secondPublicationDate) {
        log.info("between2PublicationDate endpoint in execution!");

        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(newsService.retrieveByBeetwen2PublicationDate(firstPublicationDate, secondPublicationDate));

        log.info("between2PublicationDate endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header!", "beetwen2PublicationDate")
                .body(newsDtoList);
    }


    @Operation(
            summary = "Retrieve news written by a journalist.",
            description = "Retrieve news written by a journalist.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "News retrieved successfully!"),
            @ApiResponse(responseCode = "400", description = "Input not valid."),
            @ApiResponse(responseCode = "404", description = "News not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")

    })
    @GetMapping({"journalist/{journalistCode}"})
    public ResponseEntity<List<NewsDto>> getByJournalist(
            @Parameter(description = "journalist code", example = "xxxxxxxxxxxxxxxxxxxx")
            @PathVariable @NotBlank @Size(min = 20, max = 30) String journalistCode) {
        log.info("getByJournalist endpoint in execution!");

        List<NewsDto> newsDtoList = NewsMapper.INSTANCE.toNewsDtoList(
                newsService.retrieveByJournalist(journalistCode));

        log.info("getByJournalist endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "an header!")
                .body(newsDtoList);
    }


    @Operation(
            summary = "Retrieve news with a positive vote greater than a number",
            description = "Retrieve news with a positive vote greater than a number.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "News retrieved successfully."),
            @ApiResponse(responseCode = "400", description = "Input not valid."),
            @ApiResponse(responseCode = "404", description = "News not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")

    })
    @GetMapping("/posVoteEqualMajor/{minorBound}")
    public ResponseEntity<List<NewsDto>> getPosVtEqMaj(
            @Parameter(description = "minor bound", example = "15") @PathVariable @Min(0) int minorBound) {
        log.info("getByPosVoteEqMaj in execution");

        List<NewsDto> newsDtos = NewsMapper.INSTANCE.toNewsDtoList(
                newsService.retByPosVtEqMaj(minorBound));

        log.info("getByPosVoteEqMaj executed successfully.");
        return ResponseEntity
                .ok()
                .header("", "")
                .body(newsDtos);
    }


    @Operation(
            summary = "Retrieve news with a specific tag.",
            description = "Retrieve news with a specific tag.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "News retrieved successfully."),
            @ApiResponse(responseCode = "400", description = "Input not valid."),
            @ApiResponse(responseCode = "404", description = "News not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")

    })
    @GetMapping("/tag/{tagId}")
    public ResponseEntity<List<NewsDto>> getByTag(@Parameter(description = "tag id", example = "1") @PathVariable @Min(1) int tagId){
        return null;
    }
}

