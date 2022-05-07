package com.sdardew.coffeeorderserver.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
  private final UUID productId;
  private String productName;
  private Category category;
  private long price;
  private String description;
  private final LocalDateTime cratedAt;
  private LocalDateTime updatedAt;

  public Product(UUID productId, String productName, Category category, long price) {
    this.productId = productId;
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.cratedAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public Product(UUID productId, String productName, Category category, long price, String description, LocalDateTime cratedAt, LocalDateTime updatedAt) {
    this.productId = productId;
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.description = description;
    this.cratedAt = cratedAt;
    this.updatedAt = updatedAt;
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

  public LocalDateTime getCratedAt() {
    return cratedAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setProductName(String productName) {
    this.productName = productName;
    this.updatedAt = LocalDateTime.now();
  }

  public void setCategory(Category category) {
    this.category = category;
    this.updatedAt = LocalDateTime.now();
  }

  public void setPrice(long price) {
    this.price = price;
    this.updatedAt = LocalDateTime.now();
  }

  public void setDescription(String description) {
    this.description = description;
    this.updatedAt = LocalDateTime.now();
  }
}
