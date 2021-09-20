package com.example.learningspring.exception;

import java.util.*;

public class ErrorResponse {

  private Date timestamp;
  private int status;
  private String errors;
  private List<String> message = new ArrayList<>();

  public ErrorResponse(Date timestamp, int status, String error, List<String> message) {
    super();
    this.timestamp = timestamp;
    this.status = status;
    this.errors = error;
    this.message = message;
  }

  public ErrorResponse(List<String> message, String error) {
    super();
    this.errors = error;
    this.message = message;

  }

  public Date getTimestamp() {
    return timestamp;
  }
  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public List<String> getMessage() {
    return message;
  }

  public void setMessage(List<String> message) {
    this.message = message;
  }

  public String getErrors() {
    return errors;
  }

  public void setErrors(String errors) {
    this.errors = errors;
  }

}
