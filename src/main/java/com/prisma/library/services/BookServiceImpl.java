package com.prisma.library.services;

import com.prisma.library.models.Book;
import com.prisma.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BookServiceImpl
    implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAvailableBooks() {

        List<Book> books = bookRepository.getAvailableBooks();
        return books;
    }

    @Override
    public List<Book> getBorrowedBooks(final Long userId, final Timestamp from, final Timestamp to) {

        return bookRepository.getBorrowedBooks(userId, from, to);
    }
}
