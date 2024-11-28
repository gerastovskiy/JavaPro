package ru.cource.task56.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.cource.task56.model.User;
import ru.cource.task56.service.UserService;
import java.sql.SQLException;
import java.util.List;

@RestController
@Hidden
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/getById/{id}")
    public User getUser(@PathVariable("id") Long id) throws SQLException {
        return userService.getUser(id);
    }

    @GetMapping(value = "/user/getByUsername/{username}")
    public User getUser(@PathVariable("username") String username) throws SQLException {
        return userService.getUser(username);
    }

    @GetMapping(value = "/user/getAll")
    public List<User> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/user/post")
    public HttpStatus createUser(@RequestBody @Valid User user) throws SQLException {
        userService.createUser(user);
        return HttpStatus.CREATED;
    }

    @DeleteMapping(value = "/user/deleteByAccount/{username}")
    public HttpStatus deleteUser(@PathVariable("username") String username) throws SQLException {
        userService.deleteUser(username);
        return HttpStatus.OK;
    }
}
