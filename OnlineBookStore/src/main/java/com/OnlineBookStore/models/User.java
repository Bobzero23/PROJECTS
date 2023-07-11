package com.OnlineBookStore.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    private String phone;
    private String username;
    private String address;
    private String password;
    private String role;
}
