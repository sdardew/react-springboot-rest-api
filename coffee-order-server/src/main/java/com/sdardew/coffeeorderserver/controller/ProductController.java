package com.sdardew.coffeeorderserver.controller;

import com.sdardew.coffeeorderserver.model.Product;
import com.sdardew.coffeeorderserver.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/")
  public String homePage(Model model) {
    List<Product> products = productService.getAllProducts();
    model.addAttribute("products", products);
    return "home";
  }

  @GetMapping("/product")
  public String productPage(@RequestParam("id") UUID productId, Model model) {
    Optional<Product> found = productService.getProductById(productId);
    if(found.isPresent()) {
      model.addAttribute("product", found.get());
      return "product";
    }
    return "redirect:/";
  }


  @GetMapping("/new-product")
  public String newProduct() {
    return "new-product";
  }

  @PostMapping("/new-product")
  public String newProduct(NewProduct newProduct) {
    productService.createProduct(
      newProduct.getProductName(),
      newProduct.getCategory(),
      newProduct.getPrice(),
      newProduct.getDescription());
    return "redirect:/";
  }
}
