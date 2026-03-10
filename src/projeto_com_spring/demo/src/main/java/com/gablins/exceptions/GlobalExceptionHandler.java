package com.gablins.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler
{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex)
    {
        CustomizedMessage customizedMessage = new CustomizedMessage(LocalDateTime.now().toString(),ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.ok(customizedMessage);
    }
    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<?> handleClienteNotFoundException(Exception ex)
    {
        CustomizedMessage customizedMessage = new CustomizedMessage(LocalDateTime.now().toString(),ex.getMessage(),HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(customizedMessage);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(Exception ex)
    {
        CustomizedMessage customizedMessage = new CustomizedMessage(LocalDateTime.now().toString(),ex.getMessage(),HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(customizedMessage);
    }




}
