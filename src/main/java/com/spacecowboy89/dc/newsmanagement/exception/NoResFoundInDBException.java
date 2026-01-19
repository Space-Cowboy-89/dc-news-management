package com.spacecowboy89.dc.newsmanagement.exception;

import java.time.LocalDateTime;

public class NoResFoundInDBException extends GeneralException{

    public NoResFoundInDBException(){}

    public NoResFoundInDBException(String message, LocalDateTime dateTime){
        super(message, dateTime);
    }
}
