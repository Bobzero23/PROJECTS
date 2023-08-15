package com.OnlineBookStore.controllers;


import com.OnlineBookStore.models.User;
import com.OnlineBookStore.repository.UserRepository;
import com.OnlineBookStore.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping(value = {"/userPage"})
    public String displayUserPage() {
        return "register_user";
    }


    @PostMapping("/addNewUser")
    public ResponseEntity<String> addUser(@RequestBody User user){
        User newUser = new User();

        newUser.setAddress(user.getAddress());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        newUser.setPhone(user.getPhone());
        newUser.setUsername(user.getUsername());

        userService.addUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("New user added successfully..");
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        User authenticatedUser = userService.authenticateUser(username, password);

        if (authenticatedUser != null && authenticatedUser.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.OK).body(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted user with id: " + id);
    }

    @PutMapping("/editUser/{id}")
    public ResponseEntity<User> editUserById(@PathVariable Long id, @RequestBody User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("invalid User Id: " + id));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setPassword(updatedUser.getPassword());

        userService.addUser(existingUser);
        return ResponseEntity.ok(existingUser);
    }
}


