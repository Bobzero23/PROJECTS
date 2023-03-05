package com.definex.definex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
public class Application {
    @Id
    @NotNull
    @NotBlank
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surName;
    @NotBlank
    private double income;
    @NotBlank
    private String birthDate;
    @NotBlank
    private String mobileNumber;
}
