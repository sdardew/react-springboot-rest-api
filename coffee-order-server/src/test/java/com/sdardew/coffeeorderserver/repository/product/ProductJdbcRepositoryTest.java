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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v8_0_11;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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

  private static final Product product1 = new Product(UUID.randomUUID(), "product1", Category.COFFEE_BEAN, 1000L);
  private static final Product product2 = new Product(UUID.randomUUID(), "product2", Category.SYRUP, 2000L);

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

  @Test
  @DisplayName("중복된 상품은 추가할 수 있다")
  void testInsertException() {
    repository.insert(product1);
    assertThrows(DuplicateKeyException.class, () -> repository.insert(product1));
  }

  @Test
  @DisplayName("상품의 id를 통해서 상품을 찾을 수 있다")
  void testFindById() {
    repository.insert(product1);
    Optional<Product> found = repository.findById(product1.getProductId());
    assertThat(found.isPresent(), is(true));
  }

  @Test
  @DisplayName("존재하지 않는 id로는 상품을 조회할 수 없다")
  void testFindByIdFail() {
    repository.insert(product1);
    Optional<Product> found = repository.findById(product2.getProductId());
    assertThat(found.isEmpty(), is(true));
  }

  @Test
  @DisplayName("상품명으로 상품을 조회할 수 있다")
  void testFindByName() {
    repository.insert(product1);
    Optional<Product> found = repository.findByName(product1.getProductName());
    assertThat(found.isPresent(), is(true));
  }

  @Test
  @DisplayName("존재하지 않는 상품명으로 상품을 조회할 수 없다")
  void testFindByNameFail() {
    repository.insert(product1);
    Optional<Product> found = repository.findByName(product2.getProductName());
    assertThat(found.isEmpty(), is(true));
  }

  @Test
  @DisplayName("카테고리를 통해 상품을 조회할 수 있다")
  void testFindByCategory() {
    repository.insert(product1);
    List<Product> list = repository.findByCategory(Category.COFFEE_BEAN);
    assertThat(list.isEmpty(), is(false));
  }

  @Test
  @DisplayName("상품의 정보를 업데이트할 수 있다")
  void testUpdate() {
    repository.insert(product1);
    product1.setPrice(2000);
    repository.update(product1);
    Optional<Product> product = repository.findById(product1.getProductId());
    assertThat(product.get().getPrice(), is(2000L));
  }

  @Test
  @DisplayName("id를 통해서 상품을 삭제할 수 있다")
  void testDelete() {
    repository.insert(product1);
    repository.deleteById(product1.getProductId());
    Optional<Product> product = repository.findById(product1.getProductId());
    assertThat(product.isEmpty(), is(true));
  }
}