package com.spacecowboy89.dc.newsmanagement.exception.exchandler;

import com.spacecowboy89.dc.newsmanagement.exception.InvalidInputException;
import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResFoundInDBException.class)
    public ResponseEntity<String> NoResFoundInDbCatcher() {
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("header", "Retrieve resource!!!");
        return new ResponseEntity(
                "Error, resource not found!",
                httpHeader,
                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> invalistResponseEntityCatcher() {
        return badRequest();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgNotValidExcCatcher() {
        return badRequest();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolExcCatcher() {
        return badRequest();
    }


    public ResponseEntity<String> badRequest() {
        return ResponseEntity
                .badRequest()
                .header("Errore", "Errore")
                .body("Input invalid!");
    }
}
