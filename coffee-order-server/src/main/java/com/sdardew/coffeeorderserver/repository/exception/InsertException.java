package com.sdardew.coffeeorderserver.repository.exception;

public class InsertException extends RuntimeException {
  public InsertException() {
    super("Failed To Insert To DB");
  }
}
