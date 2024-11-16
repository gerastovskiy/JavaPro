package ru.cource.task5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.cource.task5.model.Product;
import ru.cource.task5.service.ProductService;
import java.sql.SQLException;
import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/getById/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws SQLException {
        return productService.getProduct(id);
    }

    @GetMapping(value = "/product/getByAccountNumber/{accountNumber}")
    public Product getProduct(@PathVariable("accountNumber") String accountNumber) throws SQLException {
        return productService.getProduct(accountNumber);
    }

    @GetMapping(value = "/product/getByUser/{id}")
    public List<Product> getProductByUser(@PathVariable("id") Long id) throws SQLException {
        return productService.getProductsByUser(id);
    }

    @GetMapping(value = "/product/getAll")
    public List<Product> getAllProducts() throws SQLException {
        return productService.getAllProducts();
    }

    @PostMapping(value = "/product/post")
    public HttpStatus createProduct(@RequestBody Product product) throws SQLException {
        productService.createProduct(product);
        return HttpStatus.CREATED;
    }

    @DeleteMapping(value = "/product/deleteByAccount/{accountNumber}")
    public HttpStatus deleteProduct(@PathVariable("accountNumber") String accountNumber) throws SQLException {
        productService.deleteProduct(accountNumber);
        return HttpStatus.OK;
    }
}
