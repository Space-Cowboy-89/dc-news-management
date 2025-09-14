package com.spacecowboy89.dc.newsmanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsDto extends NewsInfoDto{
    private CommentDto commentDto;

    @NotNull
    private EmployeeDto employeeDto;

    @NotNull
    private List<NewsTextDto> testi;

    @NotNull
    private String categoryName;

    @NotNull
    private String newsCode;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private long positiveVote;

    @NotNull
    private long negativeVote;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class NewsTextDto{
        @NotNull
        private String newsTextCode;
        @NotNull
        private List<String> text;
        @NotNull
        private short orderNum;
        @NotNull
        private char type;
    }

}
