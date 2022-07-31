package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
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
        userDao.save(passwordCoder(user));

    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteById(id);

    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @PostConstruct
    @Override
    public void addUser() {
        Set<Role> roles1 = new HashSet<>();
        roles1.add(roleDao.findById(1L).orElse(null));
        Set<Role> roles2 = new HashSet<>();
        roles2.add(roleDao.findById(1L).orElse(null));
        roles2.add(roleDao.findById(2L).orElse(null));
        User user1 = new User(1L, "Артёмка", "Игнатьев", "ignatyev@mail.ru", 8, "младший",
                "12345", roles1);
        User user2 = new User(2L, "Артём", "Коннов", "konnov@gmail.com", 9, "старший",
                "12345", roles2);
        saveUser(user1);
        saveUser(user2);
    }

    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return user;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

}
