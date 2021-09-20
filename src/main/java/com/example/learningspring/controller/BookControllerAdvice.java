package com.example.learningspring.controller;

import com.example.learningspring.exception.BookAlreadyExists;
import com.example.learningspring.exception.BookNotFoundException;
import com.example.learningspring.exception.ErrorResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.validation.BindingResult;
import java.util.*;

@ControllerAdvice(assignableTypes = BookController.class)
public class BookControllerAdvice {

  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException exception) {
    List<String> errorList = Arrays.asList(exception.getMessage());
    ErrorResponse error = new ErrorResponse(new Date(),HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name() , errorList);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(BookAlreadyExists.class)
  public ResponseEntity<ErrorResponse> handleBookAlreadyExistsException(BookAlreadyExists exception) {
    List<String> errorList = Arrays.asList(exception.getMessage());
    ErrorResponse error = new ErrorResponse(new Date(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name() , errorList);
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<ErrorResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
    List<String> errorList = Arrays.asList(exception.getMessage());
    ErrorResponse error = new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name() , errorList);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception exception) {
    List<String> errorList = Arrays.asList(exception.getMessage());
    ErrorResponse error = new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name() , errorList);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    BindingResult result = exception.getBindingResult();
    List<String> errorList = new ArrayList<>();
    result.getFieldErrors().forEach((fieldError) ->{
      errorList.add(fieldError.getDefaultMessage());
    });
    ErrorResponse error = new ErrorResponse(new Date(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name() , errorList);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

}
