package com.prisma.library.services;

import com.prisma.library.models.Book;

import java.sql.Timestamp;
import java.util.List;

public interface BookService {

    List<Book> getAvailableBooks();

    List<Book> getBorrowedBooks(final Long userId, final Timestamp from, final Timestamp to);
}
