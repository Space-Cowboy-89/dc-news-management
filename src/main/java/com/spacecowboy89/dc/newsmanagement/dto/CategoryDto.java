package com.spacecowboy89.dc.newsmanagement.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {
    @NotNull
    @Min(1)
    private int id;

    @NotBlank
    @Size(min=20, max=20)
    private String categoryCode;

    @NotBlank
    private String name;
}
