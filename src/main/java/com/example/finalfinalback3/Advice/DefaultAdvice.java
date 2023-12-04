package com.example.finalfinalback3.Advice;

import com.example.finalfinalback3.Exceptions.PasswordsNotSameException;
import com.example.finalfinalback3.Exceptions.UserAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.UserNotFoundException;
import com.example.finalfinalback3.Model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
//public class DefaultAdvice {
//
//    @ExceptionHandler({UserAlreadyExistsException.class,
//            PasswordsNotSameException.class})
//    public ResponseEntity<Response> handleException(Exception e){
//        Response response = new Response(e.getMessage() + " something more for PNS exception");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//}
