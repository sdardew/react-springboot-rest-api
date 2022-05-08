package com.sdardew.coffeeorderserver.model.order;

import com.sdardew.coffeeorderserver.model.product.Category;

import java.util.UUID;

public class OrderItem {
  private UUID productId;
  private Category category;
  private long price;
  private int quantity;

  public OrderItem(UUID productId, Category category, long price, int quantity) {
    this.productId = productId;
    this.category = category;
    this.price = price;
    this.quantity = quantity;
  }

  public UUID getProductId() {
    return productId;
  }

  public void setProductId(UUID productId) {
    this.productId = productId;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
