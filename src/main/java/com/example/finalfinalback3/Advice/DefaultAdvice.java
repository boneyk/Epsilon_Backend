package com.example.finalfinalback3.Advice;

import com.example.finalfinalback3.Exceptions.AccessException;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Exceptions.PasswordsNotSameException;
import com.example.finalfinalback3.Model.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler({
            DataAlreadyExistsException.class,
            PasswordsNotSameException.class,
            DataNotFoundException.class,
            AccessException.class
    })
    public ResponseEntity handleException(Exception e){
        ResponseException response = new ResponseException(e.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
