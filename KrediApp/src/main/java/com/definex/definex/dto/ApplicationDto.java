package com.definex.definex.dto;


import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationDto {

    @Id
    private long id;
    private String name;
    private String surName;
    private double income;
    private String birthDate;
    private String mobileNumber;
}
