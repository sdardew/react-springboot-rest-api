package com.sdardew.coffeeorderserver.repository.exception;

public class FailToInsertException extends RuntimeException {
  public FailToInsertException() {
    super("Failed To Insert To DB");
  }
}
