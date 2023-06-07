package com.GuestBook.service;

import com.GuestBook.Repository.GuestBookRepository;
import com.GuestBook.model.GuestBook;
import org.springframework.stereotype.Service;

@Service
public class GuestBookService {
    private final GuestBookRepository bookGuestRepository;

    public GuestBookService(GuestBookRepository bookGuestRepository) {
        this.bookGuestRepository = bookGuestRepository;
    }

    public void saveGuestBook(GuestBook bookGuest) {bookGuestRepository.save(bookGuest);
    }
}
