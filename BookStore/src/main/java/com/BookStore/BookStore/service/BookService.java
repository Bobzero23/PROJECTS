package com.BookStore.BookStore.service;

import com.BookStore.BookStore.model.Book;
import com.BookStore.BookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    /**a method to save a book to data base*/
    public void save(Book book){
        bookRepository.save(book);
    }

    /**a method to return a table from database*/
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**this method should return optional but adding get() saved the day */
    public Book getBookById(int id) {
        return bookRepository.findById(id).get();
    }
}
