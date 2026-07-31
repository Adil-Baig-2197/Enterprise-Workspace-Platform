package com.ewp.user_service.exception;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> errorMap = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMap.put(field,message);
        });
        return errorMap;
    }
    @ExceptionHandler(InvalidRoleException.class)
    public Map<String,String> handleInvalidRoleException(InvalidRoleException e){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Message","Invalid role entered from exception");
        return errorMap;
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public Map<String,String> handleEmailAlreadyExistsException(EmailAlreadyExistsException e){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Message","Email already exist");
        return errorMap;
    }
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> handleUserNotFoundException(UserNotFoundException e){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Message","User not found");
        return errorMap;
    }

}