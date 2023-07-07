package com.OnlineBookStore.services;

import com.OnlineBookStore.models.User;
import com.OnlineBookStore.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean authenticateUser(String username, String  password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getUser_password().equals(password);
    }

}
