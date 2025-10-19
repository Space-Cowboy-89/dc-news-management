package com.spacecowboy89.dc.newsmanagement.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    @NotNull
    private long id;

    @NotBlank
    private String text;

    @NotNull
    @Min(0)
    private long positiveVote;

    @NotNull
    @Min(0)
    private long negativeVote;

    @NotNull
    private LocalDateTime date;

    @NotBlank
    @Size(min = 20, max = 20)
    private String commentCode;

    @NotNull
    @Min(1)
    private int userId;


    @Min(1)
    private int newsId;

    @Min(1)
    private int reviewId;

    @Min(1)
    private int previewId;

    @Min(1)
    private int commentId;


}
