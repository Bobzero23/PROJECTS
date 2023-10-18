package com.BlogBackend.dto.request;

import lombok.Data;

@Data
public class SigninRequest {
    String usernameOrEmail;
    String password;
}
