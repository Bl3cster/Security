package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.Encoder;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Encoder encoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, Encoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getReferenceById(id);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(User user) {
        userDao.save(encoder.passwordCoder(user));

    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteById(id);

    }
}
