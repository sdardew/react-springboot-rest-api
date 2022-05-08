package com.sdardew.coffeeorderserver.repository.product;

import com.sdardew.coffeeorderserver.model.product.UpdateProduct;
import com.sdardew.coffeeorderserver.model.product.Category;
import com.sdardew.coffeeorderserver.model.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
  List<Product> findAll();

  Product insert(Product product);

  Product update(Product product);

  UpdateProduct update(UpdateProduct product);

  Optional<Product> findById(UUID productId);

  Optional<Product> findByName(String productName);

  List<Product> findByCategory(Category category);

  void deleteById(UUID productId);

  void deleteAll();
}
