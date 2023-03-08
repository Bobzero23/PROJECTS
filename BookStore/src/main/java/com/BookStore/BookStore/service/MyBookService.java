package com.BookStore.BookStore.service;

import com.BookStore.BookStore.model.MyBookList;
import com.BookStore.BookStore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBookService {

    @Autowired
    private MyBookRepository myBookRepository;

    public void saveMyBooks(MyBookList mybookList){
        myBookRepository.save(mybookList);
    }


}
