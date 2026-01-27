package com.spacecowboy89.dc.newsmanagement.utility.exhandler;

import com.spacecowboy89.dc.newsmanagement.dto.ErrorResponse;
import com.spacecowboy89.dc.newsmanagement.exception.InvalidInputException;
import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.exception.PersistenceDataException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResFoundInDBException.class)
    public ResponseEntity<ErrorResponse> NoResFoundExHandler(NoResFoundInDBException ex) {

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getDateTime());
        return new ResponseEntity(
                errorResponse,
                null,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> dataIntegyViolExHandler(){
        return internalServerError(new ErrorResponse("InternalServerError",LocalDateTime.now()));
    }

    @ExceptionHandler(PersistenceDataException.class)
    public ResponseEntity<ErrorResponse> PersistDataExHandler(PersistenceDataException exc){
        return internalServerError(new ErrorResponse(exc.getMessage(),exc.getDateTime()));
    }


    public ResponseEntity<ErrorResponse> internalServerError(ErrorResponse errorResponse){
        return ResponseEntity
                .internalServerError()
                .header("Heeader", "new Header()")
                .body(errorResponse);
    }


    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> invalidInputExHandler(InvalidInputException ex) {
                return badRequest(new ErrorResponse(ex.getMessage(),ex.getDateTime()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> metArgNoValExHandler() {
                return badRequest(new ErrorResponse("Error",LocalDateTime.now()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constrViolExHandler() {
        return badRequest(new ErrorResponse("Error", LocalDateTime.now()));
    }


    public ResponseEntity<ErrorResponse> badRequest(ErrorResponse errorResponse) {
        return ResponseEntity
                .badRequest()
                .header("Errore", "Errore")
                .body(errorResponse);
    }
}
