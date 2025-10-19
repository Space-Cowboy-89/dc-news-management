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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
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
            summary = "Retrieve subcategory of a specific category!",
            description = "Retrieve subcategory of a specific category by id.",
            parameters = @Parameter(name = "categoryId", description = "category id of a category."))
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Retrieve subcategories with success."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parameter not valid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Subcategories not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "software error!",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @GetMapping("/subcategoriesById")
    public ResponseEntity<List<CategoryDto>> getSubcategoriesByCate2gory(@RequestParam @NotNull @Min(0) int categoryId) {
        log.info("getSubcategoriesByCategory in execution!");
        List<CategoryDto> categoryDtoList = CategoryMapper.INSTANCE.toCategoryDtoList(
                categoryService.retrieveByCategoryId(categoryId));

        log.info("getSubcategoriesByCategory executed with success!");
        return ResponseEntity
                .ok()
                .header("Header", "header")
                .body(categoryDtoList);
    }
}
