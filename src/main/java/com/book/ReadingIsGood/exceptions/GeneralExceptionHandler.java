package com.book.ReadingIsGood.exceptions;


import com.book.ReadingIsGood.dtos.responseDto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(BookNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(BookNotFoundException ex) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .dateTime(LocalDateTime.now()).build();
        return buildResponseEntity(apiError);
    }


    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(UserNotFoundException ex) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .dateTime(LocalDateTime.now()).build();
        return buildResponseEntity(apiError);
    }


    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
