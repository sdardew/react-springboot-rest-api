package com.sdardew.coffeeorderserver.model;

import com.sdardew.coffeeorderserver.model.exception.InvalidEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

  @Test
  @DisplayName("유효한 이메일 주소에는 에러가 발생하지 않는다")
  void testValidEmail() {
    Email email = new Email("hello@world.com");
  }

  @Test
  @DisplayName("유효하지 않은 이메일 주소에는 InvalidEmailException이 발생한다")
  void testInvalidEmail() {
    assertThrows(InvalidEmailException.class, () -> new Email("helloworld.com"));
    assertThrows(InvalidEmailException.class, () -> new Email("hello@world"));
  }
}