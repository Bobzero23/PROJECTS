package com.GuestBook.Repository;



import com.GuestBook.model.BookGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookGuestRepository extends JpaRepository<BookGuest, Integer> {
}
