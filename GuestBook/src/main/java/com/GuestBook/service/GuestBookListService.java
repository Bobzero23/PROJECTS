package com.GuestBook.service;

import com.GuestBook.Repository.GuestBookRepository;
import com.GuestBook.model.GuestBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookListService {

    private final GuestBookRepository guestBookRepository;

    public GuestBookListService(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    public Page<GuestBook> getAllGuestBooks(Pageable pageable) {
        return guestBookRepository.findAll(pageable);
    }

    public void deleteGuestBook(int id) {
        guestBookRepository.deleteById(id);
    }
}
