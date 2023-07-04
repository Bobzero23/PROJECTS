package com.OnlineBookStore.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String nickname;
    String phone;
    String username;
    String address;
    String password;
}
