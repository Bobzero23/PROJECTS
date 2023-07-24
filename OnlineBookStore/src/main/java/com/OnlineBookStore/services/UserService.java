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

    public User authenticateUser(String username, String  password) {
        User user = userRepository.findByUsername(username);

        if(user != null && user.getPassword().equals(password)) {
            user.setAuthenticated(true);
            return user;
        }else {
          return null;
        }
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
