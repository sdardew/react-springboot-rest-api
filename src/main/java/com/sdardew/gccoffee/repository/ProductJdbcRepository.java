package com.sdardew.gccoffee.repository;

import com.sdardew.gccoffee.model.Category;
import com.sdardew.gccoffee.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.sdardew.gccoffee.JdbcUtils.*;

@Repository
public class ProductJdbcRepository implements ProductRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public ProductJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Product> findAll() {
    return jdbcTemplate.query("select * from products", productRowMapper);
  }

  @Override
  public Product insert(Product product) {
    int update =  jdbcTemplate.update("INSERT INTO products(product_id, product_name, category, price, description, crated_at, updated_at)" +
      " VALUES (UUID_TO_BIN(:productId), :productName, :category, :price, :description, :cratedAt, :updatedAt)", toParamMap(product));
    if(update != 1) {
      throw new RuntimeException("Nothing was inserted");
    }
    return product;
  }

  @Override
  public Product update(Product product) {
    var update = jdbcTemplate.update("UPDATE products SET product_name = :productName, category = :category, price = :price, description = :description, crated_at = :cratedAt, updated_at = :updatedAt" +
      " WHERE product_id = UUID_TO_BIN(:productId)",
      toParamMap(product)
    );
    if(update != 1) {
      throw new RuntimeException("Nothing was updated");
    }
    return product;
  }

  @Override
  public Optional<Product> findById(UUID productId) {
    try {
      return Optional.of(
        jdbcTemplate.queryForObject("SELECT * FROM products WHERE product_id = UUID_TO_BIN(:productId)",
          Collections.singletonMap("productId", productId.toString().getBytes()), productRowMapper)
      );
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Product> findByName(String productName) {
    try {
      return Optional.of(
        jdbcTemplate.queryForObject("SELECT * FROM products WHERE product_name = :productName",
          Collections.singletonMap("productName", productName), productRowMapper)
      );
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Product> findByCategory(Category category) {
    return jdbcTemplate.query(
      "SELECT * FROM products WHERE category = :category",
      Collections.singletonMap("category", category.toString()),
      productRowMapper
    );
  }

  @Override
  public void deleteAll() {
    jdbcTemplate.update("DELETE FROM products", Collections.emptyMap());
  }

  private static final RowMapper<Product> productRowMapper = (resultSet, i) -> {
    var productId = toUUID(resultSet.getBytes("product_id"));
    var productName = resultSet.getString("product_name");
    var category = Category.valueOf(resultSet.getString("category"));
    var price = resultSet.getLong("price");
    var description = resultSet.getString("description");
    var cratedAt = toLocalDateTime(resultSet.getTimestamp("crated_at"));
    var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
    return new Product(productId, productName, category, price, description, cratedAt, updatedAt);
  };

  private Map<String, Object> toParamMap(Product product) {
    var paramMap = new HashMap<String, Object>();
    paramMap.put("productId", product.getProductId().toString().getBytes());
    paramMap.put("productName", product.getProductName());
    paramMap.put("category", product.getCategory().toString());
    paramMap.put("price", product.getPrice());
    paramMap.put("description", product.getDescription());
    paramMap.put("cratedAt", product.getCratedAt());
    paramMap.put("updatedAt", product.getUpdatedAt());
    return paramMap;
  }
}
