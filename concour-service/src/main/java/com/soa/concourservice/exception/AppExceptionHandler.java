package com.soa.concourservice.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception exception, WebRequest webRequest){
        exception.printStackTrace();
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                null
        );
        return new ResponseEntity<>(
                message,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException exception, WebRequest webRequest){
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                null
        );
        return new ResponseEntity<>(
                message,
                new HttpHeaders(),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> badRequestExceptionHandler(BadRequestException exception, WebRequest webRequest){
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                Arrays.asList(exception.getMessage())
        );
        return new ResponseEntity<>(
                message,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<Object> sqlExceptionHandler(SQLException exception, WebRequest webRequest){
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                new Date(),
                Arrays.asList(exception.getMessage(),exception.getErrorCode()+"")
        );
        return new ResponseEntity<>(
                message,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> requestValidationExceptionHandler(
            final MethodArgumentNotValidException exception,
            WebRequest webRequest
    ){
        List<String> list = exception.getBindingResult()
                        .getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());

        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                list
        );
        return new ResponseEntity<>(
                message,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

}
