package com.spacecowboy89.dc.newsmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralException  extends RuntimeException{
    private String message;
    private LocalDateTime dateTime;
}
