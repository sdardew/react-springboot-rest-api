package com.sdardew.coffeeorderserver.controller.api;

import com.sdardew.coffeeorderserver.model.product.Product;
import com.sdardew.coffeeorderserver.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductRestController {

  private final ProductService productService;

  public ProductRestController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/api/products")
  public List<Product> productsList() {
    return productService.getAllProducts();
  }

  @GetMapping("/api/product/{productId}")
  public ResponseEntity<Product> getProductByProductId(@PathVariable UUID productId) {
    Optional<Product> product = productService.getProductById(productId);
    return ResponseEntity.of(product);
  }
}
