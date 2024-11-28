package ru.cource.task56.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cource.task56.model.User;
import ru.cource.task56.repository.UserRepository;
import ru.cource.task56.service.UserService;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) throws SQLException {
        userRepository.createUser(user);
    }

    @Override
    public void deleteUser(String username) throws SQLException{
        userRepository.deleteUser(username);
    }

    @Override
    public void deleteAllUsers() throws SQLException {
        userRepository.deleteAllUsers();
    }

    @Override
    public User getUser(String username) throws SQLException{
        return userRepository.getUser(username);
    }

    @Override
    public User getUser(Long id) throws SQLException{
        return userRepository.getUser(id);
    }

    @Override
    public List<User> getAllUsers() throws SQLException{
        return userRepository.getAllUsers();
    }
}
