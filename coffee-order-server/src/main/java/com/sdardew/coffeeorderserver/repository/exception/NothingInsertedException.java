package com.sdardew.coffeeorderserver.repository.exception;

public class NothingInsertedException extends RuntimeException {
  public NothingInsertedException() {
    super("Nothing inserted");
  }
}
