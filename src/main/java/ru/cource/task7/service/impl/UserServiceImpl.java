package ru.cource.task7.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cource.task7.model.User;
import ru.cource.task7.repository.UserRepository;
import ru.cource.task7.service.UserService;
import java.sql.SQLException;
import java.util.ArrayList;
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
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) throws SQLException{
        userRepository.deleteByUsername(username);
    }

    @Override
    public void deleteAllUsers() throws SQLException {
        userRepository.deleteAll();
    }

    @Override
    public User getUser(String username) throws SQLException{
        return userRepository.findByUsername(username).get();
    }

    @Override
    public User getUser(Long id) throws SQLException{
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() throws SQLException{
        var list = new ArrayList<User>();
        userRepository.findAll().forEach(list::add);
        return list;
    }
}
