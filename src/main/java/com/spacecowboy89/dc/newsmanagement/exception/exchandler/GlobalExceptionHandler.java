package com.spacecowboy89.dc.newsmanagement.exception.exchandler;

import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResFoundInDBException.class)
    public ResponseEntity<String> NoResFoundInDbHandler(){
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("header", "Retrieve resource!!!");
        return new ResponseEntity(
                "Error, resource not found!",
                httpHeader,
                HttpStatus.NOT_FOUND);
    }

    /*
    @ExceptionHandler(NoResFoundInDBException.class)
    public ResponseEntity<String> metodo(){
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("header", "Retrieve resource!!!");
        return new ResponseEntity(
                "Error, resource not found!",
                httpHeader,
                HttpStatus.NOT_FOUND);
    }
    */
}
