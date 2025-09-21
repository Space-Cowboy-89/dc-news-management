package com.spacecowboy89.dc.newsmanagement.dto;


import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String text;

    @NotNull
    private long positiveVote;

    @NotNull
    private long negativeVote;

    @NotNull
    private LocalDateTime date;

    // if it's a value, the comment is of another comment
    private long commentCode;

}
