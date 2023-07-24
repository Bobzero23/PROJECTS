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

    
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/updateBook/{book_id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long book_id, @RequestBody Book updatedBook) {
        Book existingBook = bookRepository.findById(book_id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid Book Id" + book_id));

        existingBook.setId(updatedBook.getId());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setName(updatedBook.getName());
        existingBook.setDescription(updatedBook.getDescription());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setReviews(updatedBook.getReviews());
        existingBook.setPublication(updatedBook.getPublication());
        existingBook.setDescription(updatedBook.getDescription());

        bookService.addBook(existingBook);
        return ResponseEntity.ok(existingBook);
    }

    @DeleteMapping("/deleteBook/{book_id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long book_id) {
        bookRepository.deleteById(book_id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Book with id " + book_id + " deleted successfully");
    }

    @GetMapping("/searchBook")
    public ResponseEntity<List<Book>> searchBookName(@RequestParam String bookName) {
            return new ResponseEntity<List<Book>>(bookRepository.findByNameLike("%" + bookName + "%"), HttpStatus.OK);
    }


    @GetMapping("/getCartItems")
    public ResponseEntity<List<Book>> getCartItems(@RequestParam List<Long> bookIds) {
        List<Book> cartItems = bookService.getBooksByIds(bookIds);
        return ResponseEntity.ok(cartItems);
    }

}
