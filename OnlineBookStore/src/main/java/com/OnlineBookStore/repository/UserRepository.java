package com.OnlineBookStore.repository;

import com.OnlineBookStore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
