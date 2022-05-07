package com.sdardew.coffeeorderserver.repository.exception;

public class FailToUpdateException extends RuntimeException {
  public FailToUpdateException(String msg) {
    super(msg);
  }
}
