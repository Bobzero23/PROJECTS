package com.bob.controller;

import com.bob.config.JwtProvider;
import com.bob.model.User;
import com.bob.repoisotory.UserRepository;
import com.bob.request.LoginRequest;
import com.bob.response.AuthResponse;
import com.bob.service.implementation.CustomUserServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserServiceImplementation customUserDetails;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomUserServiceImplementation customUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetails = customUserDetails;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody User user) throws Exception {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String role = user.getRole();

        User isEmailExist = userRepository.findByEmail(email);

        if (isEmailExist != null) {
            throw new Exception("Email is already used with another account");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setRole(role);
        createdUser.setPassword(passwordEncoder.encode(password));

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse();
        response.setJwt(token);
        response.setMessage("New user registered successfully.");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        System.out.println(username + "---------" + password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse();
        response.setStatus(true);
        response.setJwt(token);
        response.setMessage("User signed in successfully.");
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("Signin userDetails:- " + userDetails);

        if (userDetails == null) {
            System.out.println("Signin userDetails is null:- " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("Sigin userDetails do not match!!");
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
    }
}
