package com.sdardew.coffeeorderserver.controller;

import com.sdardew.coffeeorderserver.model.Category;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateProduct {
  private UUID productId;
  private String productName;
  private Category category;
  private long price;
  private String description;
  private LocalDateTime updatedAt;

  public UpdateProduct(UUID productId, String productName, Category category, long price, String description) {
    this.productId = productId;
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.description = description;
    this.updatedAt = LocalDateTime.now();
  }

  public UUID getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public Category getCategory() {
    return category;
  }

  public long getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }


  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
