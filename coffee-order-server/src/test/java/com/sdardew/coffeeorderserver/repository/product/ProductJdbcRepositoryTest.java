package com.sdardew.coffeeorderserver.repository.product;

import com.sdardew.coffeeorderserver.model.Category;
import com.sdardew.coffeeorderserver.model.Product;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v8_0_11;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductJdbcRepositoryTest {

  static EmbeddedMysql embeddedMysql;

  @BeforeAll
  static void setup() {
    var config = aMysqldConfig(v8_0_11)
      .withCharset(Charset.UTF8)
      .withPort(2215)
      .withUser("test", "test1234!")
      .withTimeZone("Asia/Seoul")
      .build();
    embeddedMysql = anEmbeddedMysql(config)
      .addSchema("test-order_mgmt", ScriptResolver.classPathScript("schema_test.sql"))
      .start();
  }

  @AfterAll
  static void cleanup() {
    embeddedMysql.stop();
  }

  @Autowired
  ProductRepository repository;

  private final Product product1 = new Product(UUID.randomUUID(), "new-product", Category.COFFEE_BEAN, 1000L);

  @AfterEach
  void afterEach() {
    repository.deleteAll();
  }

  @Test
  @Disabled
  @DisplayName("모든 상품을 삭제할 수 있다")
  void testDeleteAll() {
    repository.insert(product1);
    repository.deleteAll();
    List<Product> all = repository.findAll();
    assertThat(all.isEmpty(), is(true));
  }

  @Test
  @DisplayName("상품을 추가할 수 있다")
  void testInsert() {
    repository.insert(product1);
    List<Product> all = repository.findAll();
    assertThat(all.isEmpty(), is(false));
  }
//
//  @Test
//  @DisplayName("상품을 추가할 수 있다")
//  void testInsertException() {
//    repository.insert(product1);
//    assertThrows(DuplicateKeyException.class, () -> )
//
//  }
}