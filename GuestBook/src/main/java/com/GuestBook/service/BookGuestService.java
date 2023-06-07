package com.GuestBook.service;

import com.GuestBook.Repository.BookGuestRepository;
import com.GuestBook.model.BookGuest;
import org.springframework.stereotype.Service;

@Service
public class BookGuestService {
    private final BookGuestRepository bookGuestRepository;

    public BookGuestService(BookGuestRepository bookGuestRepository) {
        this.bookGuestRepository = bookGuestRepository;
    }

    public void saveGuestBook(BookGuest bookGuest) {bookGuestRepository.save(bookGuest);
    }
}
