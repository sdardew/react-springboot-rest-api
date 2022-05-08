package com.sdardew.coffeeorderserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

  @Autowired
  ObjectMapper mapper;

  @Autowired
  MockMvc mvc;

  @Test
  @DisplayName("Home Test")
  void testHomePage() throws Exception {
    mvc.perform(get("/"))
      .andExpect(status().isOk())
      .andExpect(view().name("home"));
  }

  @Test
  @DisplayName("New Product Page Test")
  void testNewProductPage() throws Exception {
    mvc.perform(get("/new-product"))
      .andExpect(status().isOk())
      .andExpect(view().name("new-product"));
  }

  @Test
  @DisplayName("존재하지 않는 ID로 상품 조회할 때")
  void testProductPage() throws Exception {
    mvc.perform(get("/product?id=" + UUID.randomUUID().toString()))
      .andExpect(status().is3xxRedirection())
      .andExpect(view().name("redirect:/")); // 홈페이지로 돌아감
  }
}