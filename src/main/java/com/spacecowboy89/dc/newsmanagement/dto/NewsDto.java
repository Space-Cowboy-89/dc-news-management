package com.spacecowboy89.dc.newsmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsDto extends NewsInfoDto{
    private List<CommentDto> commentsDto;

   @NotNull
    private String categoryName;

    @NotBlank
    @Size(min=20, max=20)
    private String newsCode;

    @NotNull
    private LocalDateTime publicationDate;

    @NotNull
    @Min(0)
    private long positiveVote;

    @NotNull
    @Min(0)
    private long negativeVote;

    @NotBlank
    private String journalistCode;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class NewsTextDto{
        @NotNull
        private String newsTextCode;
        @NotNull
        private String text;
        @NotNull
        private short orderNum;
        @NotNull
        private char type;
        @NotNull
        private byte[] imageContent;
    }

}
