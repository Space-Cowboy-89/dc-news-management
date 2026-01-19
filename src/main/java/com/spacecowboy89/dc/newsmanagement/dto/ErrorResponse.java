package com.spacecowboy89.dc.newsmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
