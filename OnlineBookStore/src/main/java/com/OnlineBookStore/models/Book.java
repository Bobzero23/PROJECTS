package com.OnlineBookStore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String author;
    String publisher;
    String reviews;
    String price;
    String publicationYear;
    String description;
}
