package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {

        User loggedInUser =
                userService.login(user);

        if (loggedInUser != null) {
            session.setAttribute("userId", loggedInUser.getId());
            return "Login Successful";
        }

        return "Invalid Email or Password";
    }

    // LOGOUT
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout Successful";
    }

    // CHECK CURRENT SESSION
    @GetMapping("/me")
public Object me(HttpSession session) {

    Long userId = (Long) session.getAttribute("userId");

    if (userId == null) {
        return "No user is logged in";
    }

    return userService.getUserById(userId);
}

    // GET ALL USERS
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
