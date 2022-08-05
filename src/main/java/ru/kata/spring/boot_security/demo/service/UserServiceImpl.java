package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.Encoder;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Encoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Encoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public Set<User> findAllUser() {
        return new HashSet<>(userRepository.findAll());
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userRepository.save(encoder.passwordCoder(user));

    }

    @Transactional
    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);

    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }
}
