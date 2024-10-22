package com.example.securityimpljwt.controllers;

import com.example.securityimpljwt.User;
import com.example.securityimpljwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);

        return ResponseEntity.ok(user1);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> addAdmin(@RequestBody User user) {
        User user1 = userService.saveAdmin(user);

        return ResponseEntity.ok(user1);
    }

    @GetMapping("/user/access")
    public String userAccess() {
        return "User or Admin Access";
    }

    @GetMapping("/admin/access")
    public String adminAccess() {
        return "Admin Access";
    }

}
