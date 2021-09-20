package com.example.learningspring.exception;

public class BookAlreadyExists extends RuntimeException {
  public BookAlreadyExists(String message) {
    super(message);
  }
}
