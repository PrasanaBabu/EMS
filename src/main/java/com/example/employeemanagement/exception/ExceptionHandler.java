package com.example.employeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotFoundException.class)
    public String handleBookNotFoundException(EmployeeNotFoundException ex){
        return ex.getLocalizedMessage();
    }
}
