package com.sdardew.coffeeorderserver.model.product;

import com.sdardew.coffeeorderserver.model.product.Category;

public class NewProduct {
  private String productName;
  private Category category;
  private Long price;
  private String description;

  public NewProduct(String productName, Category category, Long price, String description) {
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.description = description;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

