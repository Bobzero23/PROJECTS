package com.bob.ecommercebackend.request;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest () {

    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
