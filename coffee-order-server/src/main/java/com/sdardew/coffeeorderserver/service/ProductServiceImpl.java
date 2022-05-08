package com.sdardew.coffeeorderserver.service;

import com.sdardew.coffeeorderserver.model.product.UpdateProduct;
import com.sdardew.coffeeorderserver.model.product.Category;
import com.sdardew.coffeeorderserver.model.product.Product;
import com.sdardew.coffeeorderserver.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Optional<Product> getProductById(UUID productId) {
    return productRepository.findById(productId);
  }

  @Override
  public List<Product> getProductsByCategory(Category category) {
    return productRepository.findByCategory(category);
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public void deleteProduct(UUID productId) {
    productRepository.deleteById(productId);
  }

  @Override
  public Product createProduct(String productName, Category category, long price, String desription) {
    Product product = new Product(UUID.randomUUID(), productName, category, price, desription, LocalDateTime.now(), LocalDateTime.now());
    return productRepository.insert(product);
  }

  @Override
  public UpdateProduct updateProduct(UpdateProduct updateProduct) {
    return productRepository.update(updateProduct);
  }
}
