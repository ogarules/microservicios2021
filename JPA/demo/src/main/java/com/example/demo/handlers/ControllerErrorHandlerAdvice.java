package com.example.demo.handlers;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerErrorHandlerAdvice {
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorHandlingDescriptor onConstraintViolationException(ConstraintViolationException e){
        String errorDescription = "Error de validacion => ";
        for(var violation : e.getConstraintViolations()){
            errorDescription += violation.getMessage();
        }

        ErrorHandlingDescriptor error = new ErrorHandlingDescriptor();
        error.setCode("100");
        error.setDescription(errorDescription);

        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorHandlingDescriptor onMethorArgumentViolationException(MethodArgumentNotValidException e){
        String errorDescription = "Error de validacion => ";
        for(var violation : e.getBindingResult().getFieldErrors()){
            errorDescription += violation.getField() + violation.getDefaultMessage();
        }

        ErrorHandlingDescriptor error = new ErrorHandlingDescriptor();
        error.setCode("100");
        error.setDescription(errorDescription);

        return error;
    }
    
    
}
