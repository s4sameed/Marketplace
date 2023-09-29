package com.example.marketplace.exception;

import com.example.marketplace.utility.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //user not found exception
    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ResponseMessage> userNotFoundExceptionHandler(UserNotFoundException userNotFoundException){

        ResponseMessage response = ResponseMessage.builder()
                .message(userNotFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .success(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, Object>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException ){
        List<ObjectError> allErrors = methodArgumentNotValidException.getBindingResult().getAllErrors();
        Map<String, Object> response = new HashMap<>();

        allErrors.stream().forEach(objectError -> {
            String msg = objectError.getDefaultMessage();
            String field = ((FieldError) objectError).getField();
            response.put(field, msg);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
