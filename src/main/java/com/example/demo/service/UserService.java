package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }
    public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
}

    public User login(User user) {

        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());

        if (dbUser.isPresent() &&
            dbUser.get().getPassword().equals(user.getPassword())) {

            return dbUser.get();
        }

        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}