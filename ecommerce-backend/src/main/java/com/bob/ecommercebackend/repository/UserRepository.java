package com.bob.ecommercebackend.repository;

import com.bob.ecommercebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
