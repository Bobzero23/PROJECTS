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
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if(userService.authenticateUser(username, password)) {
            return ResponseEntity.status(HttpStatus.OK).body("Welcome back " + username);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect username or password");
        }
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


//    @PostMapping("/logout")
//    public ResponseEntity<String> logoutUser() {
//        return ResponseEntity.status(HttpStatus.CREATED).body("Looking forward to see you again..");
//    }
}


