package ru.cource.task5.service;

import ru.cource.task5.model.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    void createProduct(Product product) throws SQLException;
    void deleteProduct(String account_number) throws SQLException;
    void deleteAllProducts() throws SQLException;
    void debitProduct(Product product, BigDecimal amount) throws SQLException;
    void creditProduct(Product product, BigDecimal amount) throws SQLException;
    Product getProduct(String account_number) throws SQLException;
    Product getProduct(Long id) throws SQLException;
    List<Product> getProductsByUser(Long id) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
}