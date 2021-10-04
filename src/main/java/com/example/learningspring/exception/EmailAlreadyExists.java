package com.example.learningspring.exception;

public class EmailAlreadyExists extends RuntimeException {
  public EmailAlreadyExists(String message) {
    super(message);
  }
}
