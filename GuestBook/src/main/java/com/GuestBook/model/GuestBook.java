package com.GuestBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class GuestBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "first name field can't be blank")
    @Size(min = 3, message = "first name must be at least 3 characters")
    private String firstName;

    @NotBlank(message = "last name name field can't be blank")
    @Size(min = 3, message = "last name must be at least 3 characters")
    private String lastName;

    @NotBlank(message = "email field can't be blank")
    @Email
    private String email;

    @NotBlank(message = "mobile number field can't be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must have 10 digits")
    private String mobileNumber;

    @NotBlank(message = "subject field can't be blank")
    private String subject;

    @NotBlank(message = "message field can't be blank")
    private String message;

    private LocalDateTime timestamp;
}
