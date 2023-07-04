package com.OnlineBookStore.repository;

import com.OnlineBookStore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
