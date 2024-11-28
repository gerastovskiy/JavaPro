package ru.cource.task56.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cource.task56.model.Product;
import ru.cource.task56.repository.ProductRepository;
import ru.cource.task56.service.ProductService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(Product product) throws SQLException {
        productRepository.createProduct(product);
    }

    @Override
    public void deleteProduct(String account_number) throws SQLException{
        productRepository.deleteProduct(account_number);
    }

    @Override
    public void deleteAllProducts() throws SQLException {
        productRepository.deleteAllProducts();
    }

    @Override
    public void debitProduct(Product product, BigDecimal amount) throws SQLException {
        product.setAccountBalance(product.getAccountBalance().subtract(amount));
        productRepository.updateProduct(product);
    }

    @Override
    public void creditProduct(Product product, BigDecimal amount) throws SQLException {
        product.setAccountBalance(product.getAccountBalance().add(amount));
        productRepository.updateProduct(product);
    }

    @Override
    public Product getProduct(String account_number) throws SQLException{
        return productRepository.getProduct(account_number);
    }

    @Override
    public List<Product> getAllProducts() throws SQLException{
        return productRepository.getAllProducts();
    }

    @Override
    public Product getProduct(Long id) throws SQLException {
        return productRepository.getProduct(id);
    }

    @Override
    public List<Product> getProductsByUser(Long id) throws SQLException {
        return productRepository.getProductsByUser(id);
    }
}
