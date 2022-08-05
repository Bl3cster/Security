package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    User getUserById(Long id);

    Set<User> findAllUser();

    void saveUser(User user);

    void deleteUserById(long id);

    User findByUsername(String username);

    UserDetails loadUserByUsername(String userName);
}
