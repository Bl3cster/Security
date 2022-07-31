package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User getUserById(Long id);

    List<User> findAllUser();

    void saveUser(User user);

    void deleteUserById(long id);

    User findByUsername(String username);

    void addUser();

    User passwordCoder(User user);
}
