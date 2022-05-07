package com.sdardew.coffeeorderserver.repository.product;

import com.sdardew.coffeeorderserver.model.Category;
import com.sdardew.coffeeorderserver.model.Product;
import com.sdardew.coffeeorderserver.repository.exception.FailToInsertException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.sdardew.coffeeorderserver.repository.JdbcUtils.toLocalDateTime;
import static com.sdardew.coffeeorderserver.repository.JdbcUtils.toUUID;

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
      throw new FailToInsertException();
    }
    return product;
  }

  @Override
  public Product update(Product product) {
    return null;
  }

  @Override
  public Optional<Product> findById(UUID productId) {
    return Optional.empty();
  }

  @Override
  public Optional<Product> findByName(String productId) {
    return Optional.empty();
  }

  @Override
  public List<Product> findByCategory(Category category) {
    return null;
  }

  @Override
  public void deleteAll() {

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
