package com.spacecowboy89.dc.newsmanagement.web;


import com.spacecowboy89.dc.newsmanagement.dto.CategoryDto;
import com.spacecowboy89.dc.newsmanagement.dto.ErrorResponse;
import com.spacecowboy89.dc.newsmanagement.service.CategoryService;
import com.spacecowboy89.dc.newsmanagement.utility.mapper.CategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Validated
@Slf4j
@Tag(name = "category", description = "These endpoints work with category!")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Operation(
            summary = "Retrieve categories by a category_id.",
            description = "Retrieve categories by a specific category_id.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Categories retrieved successfully."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Input not valid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categories not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error!",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @GetMapping("{categoryId}")
    public ResponseEntity<List<CategoryDto>> getByCategoryId(@Parameter(description = "category id", example = "1") @PathVariable @NotNull @Min(0) int categoryId) {
        log.info("getByCategoryId endpoint in execution!");
        List<CategoryDto> response = CategoryMapper.INSTANCE.toCategoryDtoList(
                categoryService.retrieveByCategoryId(categoryId));

        log.info("getByCategoryId endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "header")
                .body(response);
    }
}