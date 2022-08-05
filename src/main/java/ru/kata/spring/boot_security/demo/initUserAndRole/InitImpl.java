package ru.kata.spring.boot_security.demo.initUserAndRole;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

public class InitImpl implements initUserAndRole {

    private final RoleRepository roleRepository;
    private final UserService userService;


    @Autowired
    public InitImpl(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    @PostConstruct
    public void addRole() {
        roleRepository.save(new Role(1L, "ROLE_USER"));
        roleRepository.save(new Role(2L, "ROLE_ADMIN"));

    }

    @Override
    @PostConstruct
    public void addUser() {
        Set<Role> roles1 = new HashSet<>();
        roles1.add(roleRepository.findById(1L).orElse(null));
        Set<Role> roles2 = new HashSet<>();
        roles2.add(roleRepository.findById(1L).orElse(null));
        roles2.add(roleRepository.findById(2L).orElse(null));
        User user1 = new User(1L, "Артёмка", "Игнатьев", "ignatyev@mail.ru", 8, "младший",
                "12345", roles1);
        User user2 = new User(2L, "Артём", "Коннов", "konnov@gmail.com", 9, "старший",
                "12345", roles2);
        userService.saveUser(user1);
        userService.saveUser(user2);
    }
}
