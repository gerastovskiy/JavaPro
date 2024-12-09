package ru.cource.task78.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cource.task78.model.Product;
import ru.cource.task78.repository.ProductRepository;
import ru.cource.task78.service.ProductService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
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
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String account_number) throws SQLException{
        productRepository.deleteByAccountNumber(account_number);
    }

    @Override
    public void deleteAllProducts() throws SQLException {
        productRepository.deleteAll();
    }

    @Override
    public void debitProduct(Product product, BigDecimal amount) throws SQLException {
        product.setAccountBalance(product.getAccountBalance().subtract(amount));
        productRepository.save(product);
    }

    @Override
    public void creditProduct(Product product, BigDecimal amount) throws SQLException {
        product.setAccountBalance(product.getAccountBalance().add(amount));
        productRepository.save(product);
    }

    @Override
    public Product getProduct(String account_number) throws SQLException{
        return productRepository.findByAccountNumber(account_number).get();
    }

    @Override
    public List<Product> getAllProducts() throws SQLException{
        var list = new ArrayList<Product>();
        productRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Product getProduct(Long id) throws SQLException {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getProductsByUser(Long id) throws SQLException {
        return productRepository.findByUserId(id);
    }
}
