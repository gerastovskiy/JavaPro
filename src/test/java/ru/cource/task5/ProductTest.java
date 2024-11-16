package ru.cource.task5;

import org.junit.jupiter.api.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.cource.task5.model.Product;
import ru.cource.task5.model.ProductType;
import ru.cource.task5.model.User;
import ru.cource.task5.service.ProductService;
import ru.cource.task5.service.UserService;
import java.math.BigDecimal;
import java.sql.SQLException;

public class ProductTest {
    String username = "Alex";
    String accountNumberFirst = "40817810000000000999";
    String accountNumberSecond = "40817810000000000888";

    @Test
    @BeforeEach
    public void emptyTable() throws SQLException {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("ru.cource.task5");
        ctx.getBean(ProductService.class).deleteAllProducts();
        ctx.getBean(UserService.class).deleteAllUsers();
        ctx.close();
    }

    @Test
    public void createAndGetProductTest() throws SQLException {
        var userInsert = new User(0L, username);

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("ru.cource.task5");
        ctx.getBean(UserService.class).createUser(userInsert);
        var userSelect = ctx.getBean(UserService.class).getUser(username);

        var productInsert = new Product(0L, userSelect.getId(), accountNumberFirst, BigDecimal.valueOf(999.99), ProductType.ACCOUNT);
        ctx.getBean(ProductService.class).createProduct(productInsert);
        productInsert = ctx.getBean(ProductService.class).getProduct(accountNumberFirst);

        ctx.close();

        Assertions.assertEquals(productInsert.getAccountNumber(), productInsert.getAccountNumber());
    }

    @Test
    public void createAndDeleteProductTest() throws SQLException {
        var userInsert = new User(0L, username);

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("ru.cource.task5");
        ctx.getBean(UserService.class).createUser(userInsert);
        var userSelect = ctx.getBean(UserService.class).getUser(username);
        var productInsert = new Product(0L, userSelect.getId(), accountNumberFirst, BigDecimal.valueOf(999.99), ProductType.ACCOUNT);
        ctx.getBean(ProductService.class).createProduct(productInsert);
        ctx.getBean(ProductService.class).deleteProduct(accountNumberFirst);
        var products = ctx.getBean(ProductService.class).getAllProducts();

        ctx.close();

        Assertions.assertEquals(true, products.isEmpty());
    }

    @Test
    public void getAllProductsTest() throws SQLException {
        var userInsert = new User(0L, username);

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("ru.cource.task5");
        ctx.getBean(UserService.class).createUser(userInsert);
        var userSelect = ctx.getBean(UserService.class).getUser(username);
        var productFirst = new Product(0L, userSelect.getId(), accountNumberFirst, BigDecimal.valueOf(999.99), ProductType.ACCOUNT);
        var productSecond = new Product(0L, userSelect.getId(), accountNumberSecond, BigDecimal.valueOf(888.88), ProductType.CARD);
        ctx.getBean(ProductService.class).createProduct(productFirst);
        ctx.getBean(ProductService.class).createProduct(productSecond);

        var products = ctx.getBean(ProductService.class).getAllProducts();

        ctx.close();

        Assertions.assertEquals(2, products.stream().count());
    }

    @Test
    public void getAllProductsbyUserTest() throws SQLException {
        var userInsert = new User(0L, username);

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("ru.cource.task5");
        ctx.getBean(UserService.class).createUser(userInsert);
        var userSelect = ctx.getBean(UserService.class).getUser(username);
        var productFirst = new Product(0L, userSelect.getId(), accountNumberFirst, BigDecimal.valueOf(999.99), ProductType.ACCOUNT);
        var productSecond = new Product(0L, userSelect.getId(), accountNumberSecond, BigDecimal.valueOf(888.88), ProductType.CARD);
        ctx.getBean(ProductService.class).createProduct(productFirst);
        ctx.getBean(ProductService.class).createProduct(productSecond);

        var products = ctx.getBean(ProductService.class).getProductsByUser(userSelect.getId());

        ctx.close();

        Assertions.assertEquals(2, products.stream().count());
    }
}
