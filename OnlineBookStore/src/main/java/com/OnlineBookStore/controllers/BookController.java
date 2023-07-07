package com.OnlineBookStore.controllers;

import com.OnlineBookStore.models.Book;
import com.OnlineBookStore.repository.BookRepository;
import com.OnlineBookStore.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }


    @CrossOrigin
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBook();
    }

    @PutMapping("/updateBook/{book_id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long book_id, @RequestBody Book updatedBook) {
        Book existingBook = bookRepository.findById(book_id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid Book Id" + book_id));

        existingBook.setBook_id(updatedBook.getBook_id());
        existingBook.setBook_author(updatedBook.getBook_author());
        existingBook.setBook_name(updatedBook.getBook_name());
        existingBook.setBook_description(updatedBook.getBook_description());
        existingBook.setBook_price(updatedBook.getBook_price());
        existingBook.setBook_publisher(updatedBook.getBook_publisher());
        existingBook.setBook_reviews(updatedBook.getBook_reviews());
        existingBook.setBook_publication_year(updatedBook.getBook_publication_year());
        existingBook.setBook_description(updatedBook.getBook_description());

        bookService.addBook(existingBook);
        return ResponseEntity.ok(existingBook);
    }

    @DeleteMapping("/deleteBook/{book_id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long book_id) {
        bookRepository.deleteById(book_id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Book with id " + book_id + " deleted successfully");
    }

}
