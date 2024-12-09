package ru.cource.task78.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.cource.task78.model.Product;
import ru.cource.task78.service.ProductService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@RestController
@Hidden
public class ProductController {
    private final ProductService productService;

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
    public HttpStatus createProduct(@RequestBody @Valid Product product) throws SQLException {
        productService.createProduct(product);
        return HttpStatus.CREATED;
    }

    @PutMapping(value = "/product/debit{id}{amount}")
    public Product debitProduct(@RequestParam("id") Long id, @RequestParam("amount") BigDecimal amount) throws SQLException {
        var product = productService.getProduct(id);
        productService.debitProduct(product, amount);
        return product;
    }

    @PutMapping(value = "/product/credit{id}{amount}")
    public Product creditProduct(@RequestParam("id") Long id, @RequestParam("amount") BigDecimal amount) throws SQLException {
        var product = productService.getProduct(id);
        productService.creditProduct(product, amount);
        return product;
    }

    @DeleteMapping(value = "/product/deleteByAccount/{accountNumber}")
    public HttpStatus deleteProduct(@PathVariable("accountNumber") String accountNumber) throws SQLException {
        productService.deleteProduct(accountNumber);
        return HttpStatus.OK;
    }
}
