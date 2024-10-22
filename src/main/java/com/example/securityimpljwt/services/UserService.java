package com.example.securityimpljwt.services;

import com.example.securityimpljwt.Role;
import com.example.securityimpljwt.User;
import com.example.securityimpljwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public User saveAdmin(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }
}
