package ru.kata.spring.boot_security.demo.security;

import ru.kata.spring.boot_security.demo.model.User;

public interface Encoder {
    User passwordCoder(User user);
}
