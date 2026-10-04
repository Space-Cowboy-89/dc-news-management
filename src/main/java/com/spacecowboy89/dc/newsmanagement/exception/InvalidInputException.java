package com.spacecowboy89.dc.newsmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



public class InvalidInputException extends GeneralException{
    public InvalidInputException(String message, LocalDateTime dateTime){
        super(message,dateTime);
    }
}