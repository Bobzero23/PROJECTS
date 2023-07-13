package com.OnlineBookStore.controllers;

import com.OnlineBookStore.models.Book;
import com.OnlineBookStore.repository.BookRepository;
import com.OnlineBookStore.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @PostMapping("/searchBook")
    public ResponseEntity<String> searchBookName(@RequestBody Book book) {
        String bookName = book.getName();

        if(bookService.isBookAvailable(bookName)) {
            return ResponseEntity.status(HttpStatus.OK).body("Yes {" + bookName +  "} book is available");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No results found");
        }
    }


}
