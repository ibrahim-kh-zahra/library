package com.prisma.library.repositories;

import com.prisma.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.Timestamp;
import java.util.List;

public interface BookRepository
    extends JpaRepository<Book, Long> {

    public Book findByTitle(String title);

    @Query("FROM Book b left join Borrow as br on b.book_id=br.book.book_id WHERE br.book IS NULL OR br.borrowedTo < current_timestamp()")
    public List<Book> getAvailableBooks();
    @Query("From Book b inner join Borrow as br on b.book_id=br.book.book_id WHERE br.user.user_id=:userId and :from <br.borrowedTo and :to > br" +
           ".borrowedFrom ")
   public List<Book> getBorrowedBooks(Long userId, Timestamp from, Timestamp to);
}
