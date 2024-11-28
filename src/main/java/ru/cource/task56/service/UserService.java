package ru.cource.task56.service;

import ru.cource.task56.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    void createUser(User user) throws SQLException;
    void deleteUser(String username) throws SQLException;
    void deleteAllUsers() throws SQLException;
    User getUser(String username) throws SQLException;
    User getUser(Long id) throws SQLException;
    List<User> getAllUsers() throws SQLException;
}