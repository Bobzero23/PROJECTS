package com.OnlineBookStore.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long category_id;
    private String category_name;

    @ManyToMany(mappedBy = "categories")
    private List<Book> books;
}
