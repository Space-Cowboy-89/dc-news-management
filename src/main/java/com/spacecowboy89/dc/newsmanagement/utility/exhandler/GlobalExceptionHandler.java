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
    public ResponseEntity<ErrorResponse> NoResFoundInDbCatcher(NoResFoundInDBException ex) {

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getDateTime());
        return new ResponseEntity(
                errorResponse,
                null,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> dataIntegrityViolationExcCatcher(){
        return internalServerError(new ErrorResponse("InternalServerError",LocalDateTime.now()));
    }

    @ExceptionHandler(PersistenceDataException.class)
    public ResponseEntity<ErrorResponse> PersistDataExcHandler(PersistenceDataException exc){
        return internalServerError(new ErrorResponse(exc.getMessage(),exc.getDateTime()));
    }


    public ResponseEntity<ErrorResponse> internalServerError(ErrorResponse errorResponse){
        return ResponseEntity
                .internalServerError()
                .header("Heeader", "new Header()")
                .body(errorResponse);
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
