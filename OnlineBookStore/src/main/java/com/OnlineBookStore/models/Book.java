package com.OnlineBookStore.models;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long book_id;
    private String book_name;
    private String book_author;
    private String book_publisher;
    private String book_reviews;
    private String book_price;
    private String book_publication_year;
    private String book_description;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
}
