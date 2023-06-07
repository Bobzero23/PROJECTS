package com.GuestBook.service;

import com.GuestBook.Repository.GuestBookRepository;
import com.GuestBook.model.GuestBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookListService {

    private final GuestBookRepository guestBookRepository;

    public GuestBookListService(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    public List<GuestBook> getAllEntries() {
        return guestBookRepository.findAll();
    }
}
