package ru.kata.spring.boot_security.demo.initUserAndRole;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

public class initUserAndRoleImpl implements initUserAndRole {

    private final RoleDao roleDao;
    private final UserService userService;


    @Autowired
    public initUserAndRoleImpl(RoleDao roleDao, UserService userService) {
        this.roleDao = roleDao;
        this.userService = userService;
    }

    @Override
    @PostConstruct
    public void addRole() {
        roleDao.save(new Role(1L, "ROLE_USER"));
        roleDao.save(new Role(2L, "ROLE_ADMIN"));

    }

    @Override
    @PostConstruct
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
        userService.saveUser(user1);
        userService.saveUser(user2);
    }
}
