package com.todolist.rest.exception;

import com.todolist.exception.EntityNotFoundException;
import com.todolist.rest.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleException(EntityNotFoundException exception) {
        return new ErrorResponse.ErrorResponseBuilder()
                .withMessage(exception.getMessage())
                .withTimeStamp(System.currentTimeMillis())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponse handleException(ValidationException exception) {
        return new ErrorResponse.ErrorResponseBuilder()
                .withMessage(exception.getMessage())
                .withTimeStamp(System.currentTimeMillis())
                .build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException exception) {
        List<ErrorResponse> exceptionList = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach(error -> {
            exceptionList.add(new ErrorResponse.ErrorResponseBuilder().withMessage((error.getDefaultMessage())).build());
        });
        return exceptionList;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(Exception exception) {
        exception.printStackTrace();
        return new ErrorResponse.ErrorResponseBuilder()
                .withMessage(exception.getMessage())
                .withTimeStamp(System.currentTimeMillis())
                .build();
    }

}
