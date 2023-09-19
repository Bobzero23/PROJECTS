package com.AuthenticatedBackend.service;

import com.AuthenticatedBackend.model.ApplicationUser;
import com.AuthenticatedBackend.model.Role;
import com.AuthenticatedBackend.repository.RoleRepository;
import com.AuthenticatedBackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ApplicationUser registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);


        return userRepository.save(new ApplicationUser(0, username, encodedPassword, authorities));
    }

}
