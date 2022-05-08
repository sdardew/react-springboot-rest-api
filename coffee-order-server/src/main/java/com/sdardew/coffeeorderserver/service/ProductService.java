package com.sdardew.coffeeorderserver.service;

import com.sdardew.coffeeorderserver.controller.UpdateProduct;
import com.sdardew.coffeeorderserver.model.Category;
import com.sdardew.coffeeorderserver.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
  Optional<Product> getProductById(UUID productId);

  List<Product> getProductsByCategory(Category category);

  List<Product> getAllProducts();

  void deleteProduct();

  Product createProduct(String productName, Category category, long price);

  Product createProduct(String productName, Category category, long price, String desription);

  UpdateProduct updateProduct(UpdateProduct updateProduct);
}
