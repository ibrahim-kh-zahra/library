package com.prisma.library.controllers;

import com.prisma.library.models.Book;
import com.prisma.library.services.BookService;
import com.prisma.library.utils.TUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/available")
    public List<Book> getAvailableBooks() {

        return bookService.getAvailableBooks();
    }

    @GetMapping("/borrowed-by")
    public List<Book> getBorrowedBook(
        @RequestParam(name = "userId")
            Long userId,
        @RequestParam
             String from,
        @RequestParam
            String to
    ) {

        return bookService.getBorrowedBooks(userId,TUtils.convertToTimestamp(from),TUtils.convertToTimestamp(to));
    }
}
