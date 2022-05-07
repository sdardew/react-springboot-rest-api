package com.sdardew.coffeeorderserver.repository.product;

import com.sdardew.coffeeorderserver.model.Category;
import com.sdardew.coffeeorderserver.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
  List<Product> findAll();

  Product insert(Product product);

  Product update(Product product);

  Optional<Product> findById(UUID productId);

  Optional<Product> findByName(String productId);

  List<Product> findByCategory(Category category);

  void deleteAll();
}
