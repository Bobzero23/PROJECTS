package com.BlogBackend.dto.request;

import lombok.Data;

@Data
public class SignupRequest {
    String username;
    String email;
    String password;
}
