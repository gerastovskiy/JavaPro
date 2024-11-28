package ru.cource.task7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.cource.task56.model.User;
import ru.cource.task56.service.ProductService;
import ru.cource.task56.service.UserService;

import java.sql.SQLException;
import java.util.NoSuchElementException;

public class UsersTest {
    String usernameFirst = "Alex";
    String usernameSecond = "Pete";

    @Test
    @BeforeEach
    public void emptyTable() throws SQLException {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("ru.cource.task7");
        ctx.getBean(ProductService.class).deleteAllProducts();
        ctx.getBean(UserService.class).deleteAllUsers();
        ctx.close();
    }

    @Test
    public void createAndGetUserTest() throws SQLException {
        var userInsert = new User(0L, usernameFirst);

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("ru.cource.task7");
        ctx.getBean(UserService.class).createUser(userInsert);
        var userSelect = ctx.getBean(UserService.class).getUser(usernameFirst);
        ctx.close();

        Assertions.assertEquals(userInsert.getUsername(), userSelect.getUsername());
    }

    @Test
    public void create2IdenticalUsers() throws SQLException {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("ru.cource.task7");

        NoSuchElementException thrown = Assertions
                .assertThrows(NoSuchElementException.class, () -> {
                    ctx.getBean(UserService.class).getUser(usernameFirst);
                }, "NoSuchElementException error was expected");

        ctx.close();

        Assertions.assertEquals("Record Not Found by username " + usernameFirst, thrown.getMessage());
    }

    @Test
    public void createAndDeleteUserTest() throws SQLException {
        var userInsert = new User(0L, usernameFirst);

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("ru.cource.task7");
        ctx.getBean(UserService.class).createUser(userInsert);
        ctx.getBean(UserService.class).deleteUser(usernameFirst);

        var users = ctx.getBean(UserService.class).getAllUsers();
        ctx.close();

        Assertions.assertEquals(true, users.isEmpty());
    }

    @Test
    public void getAllUsersTest() throws SQLException {
        var userInsertFirst = new User(0L, usernameFirst);
        var userInsertSecond = new User(0L, usernameSecond);

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("ru.cource.task7");
        ctx.getBean(UserService.class).createUser(userInsertFirst);
        ctx.getBean(UserService.class).createUser(userInsertSecond);
        var users = ctx.getBean(UserService.class).getAllUsers();
        ctx.close();

        Assertions.assertEquals(2, users.stream().count());
    }
}
