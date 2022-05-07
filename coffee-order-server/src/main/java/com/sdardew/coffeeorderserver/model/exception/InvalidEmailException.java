package com.sdardew.coffeeorderserver.model.exception;

public class InvalidEmailException extends RuntimeException {
  public InvalidEmailException() {
    super("메일 주소가 유효하지 않습니다");
  }
}
