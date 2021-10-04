package com.example.learningspring.controller;


import com.example.learningspring.exception.EmailAlreadyExists;
import com.example.learningspring.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ControllerAdvice(assignableTypes = UserController.class)
public class UserControllerAdvice {

  @ExceptionHandler(EmailAlreadyExists.class)
  public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExists exception) {
    List<String> errorList = Arrays.asList(exception.getMessage());
    ErrorResponse error = new ErrorResponse(new Date(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name() , errorList);
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }
}
