package com.BookStore.BookStore.controller;


import com.BookStore.BookStore.model.Book;
import com.BookStore.BookStore.model.MyBookList;
import com.BookStore.BookStore.service.BookService;
import com.BookStore.BookStore.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MyBookService myBookService;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister";
    }


    /**a method to display the books on the web*/
    @GetMapping("/available_books")
    public ModelAndView getAllBooks(){
       List<Book> list = bookService.getAllBooks();
/*       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("bookList");
       modelAndView.addObject("book", list);*/
        /**instead of writing these three lines we can do like this*/
        return new ModelAndView("bookList", "book", list);
    }


    /**method to save the book from register page*/
    /**then it redirects you to the available books*/
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book){
        bookService.save(book);
        return"redirect:/available_books";
    }

    /**method to display mybook page*/
    @GetMapping("/my_books")
    public String displayMyBooks(Model model){
        List<MyBookList> list = myBookService.getAllMyBooks();
        model.addAttribute("book", list);
        return "myBooks";
    }

    /**method to display books added to myBooks*/
    @GetMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book book = bookService.getBookById(id);
        MyBookList BookList = new MyBookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice());
        myBookService.saveMyBooks(BookList);
        return "redirect:/my_books";
    }

    /**path handler for book editing*/
    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "bookEdit";
    }

    /**path handler for deleting a book*/
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.deleteBookById(id);
        return "redirect:/available_books";
    }

}
