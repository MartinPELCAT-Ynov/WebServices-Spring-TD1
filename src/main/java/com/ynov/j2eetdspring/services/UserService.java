package com.ynov.j2eetdspring.services;

import com.ynov.j2eetdspring.entities.User;
import com.ynov.j2eetdspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createOrUpdate(User user) {
        return this.userRepository.save(user);
    }

    public User getUserById(String username) {
        return this.userRepository.findById(username).orElse(null);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void deleteUser(String username) {
        this.userRepository.deleteById(username);
    }
}
