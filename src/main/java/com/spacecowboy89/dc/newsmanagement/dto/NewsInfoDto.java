package com.spacecowboy89.dc.newsmanagement.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsInfoDto {
    @NotNull
    private String title;
    @NotNull
    private String summary;
    @NotNull
    private byte[] contentImage;
}

