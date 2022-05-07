package com.sdardew.coffeeorderserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

  @GetMapping("/")
  public String homePage() {
    return "home";
  }

  @GetMapping("/product-list")
  public String productListPage() {
    return "product-list";
  }
}
