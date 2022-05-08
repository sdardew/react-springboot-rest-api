package com.sdardew.coffeeorderserver.repository.order;

import com.sdardew.coffeeorderserver.model.order.Order;

public interface OrderRepository {
  Order insert(Order order);

  void deleteAll();
}
