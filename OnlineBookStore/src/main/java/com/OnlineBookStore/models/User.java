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
    private String user_phone;
    private String username;
    private String user_address;
    private String user_password;
    private String user_role;
}
