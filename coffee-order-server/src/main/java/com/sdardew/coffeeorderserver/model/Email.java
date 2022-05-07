package com.sdardew.coffeeorderserver.model;

import com.sdardew.coffeeorderserver.model.exception.InvalidEmailException;

import java.util.regex.Pattern;

public class Email {

  private final String address;

  public Email(String address) {
    if(checkAddress(address)) {
      this.address = address;
    }
    else {
      throw new InvalidEmailException();
    }
  }

  private static boolean checkAddress(String address) {
    return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b", address);
  }

  public String getAddress() {
    return address;
  }
}
