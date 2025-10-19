package com.spacecowboy89.dc.newsmanagement.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsInfoDto {
    @NotBlank
    @Size(max = 25)
    private String title;
    @NotBlank
    private String summary;

    private byte[] contentImage;
}

