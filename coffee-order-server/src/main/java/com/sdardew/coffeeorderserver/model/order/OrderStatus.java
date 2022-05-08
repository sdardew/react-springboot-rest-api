package com.sdardew.coffeeorderserver.model.order;

public enum OrderStatus {
  ACCEPTED,
  PAYMENT_CONFIRMED,
  READY_FOR_DELIVERY,
  SHIPPED,
  SETTLED,
  CANCELLED
}
