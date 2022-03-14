package com.prisma.library.config;

import com.prisma.library.models.Book;
import com.prisma.library.models.Borrow;
import com.prisma.library.models.Gender;
import com.prisma.library.models.User;
import com.prisma.library.repositories.BookRepository;
import com.prisma.library.repositories.BorrowRepository;
import com.prisma.library.repositories.UserRepository;
import com.prisma.library.utils.CSVFileUtils;
import com.prisma.library.utils.TUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Read data from csv files and load them into DB
 * Empty DB before start
 */
@Component
public class DataLoader
    implements ApplicationRunner {

    @Autowired
    DataLoaderConfig dataLoaderConfig;

    private final UserRepository   userRepository;
    private final BookRepository   bookRepository;
    private final BorrowRepository borrowRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, BookRepository bookRepository, BorrowRepository borrowRepository) {

        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
    }

    /**
     * Initial point for initializing DB with data from csv files
     * @param args
     */
    public void run(ApplicationArguments args) {

        System.out.println("Start loading data...");
        readUsers(dataLoaderConfig.getUserCsvPath());
        readBooks(dataLoaderConfig.getBookCsvPath());
        readBorrowedBooks(dataLoaderConfig.getBorrowedCsvPath());
        System.out.println("Finish loading data...");
    }

    /**
     *
     * @param filePath
     */
    public void readUsers(String filePath) {

        userRepository.deleteAll();

        List<String[]> list = CSVFileUtils.readDataFromCSV(filePath);
        list.stream()
            .skip(1)
            .forEach(line -> {
                User user = User.builder()
                                .name(line[0].concat(",")
                                             .concat(line[1]))
                                .joiningDate(TUtils.convertToTimestamp(line[2]))
                                .expiringDate(TUtils.convertToTimestamp(line[3]))
                                .gender(Gender.valueOf(line[4]))
                                .build();

                userRepository.save(user);
            });
    }

    /**
     *
     * @param filePath
     */
    public void readBooks(String filePath) {

        bookRepository.deleteAll();

        List<String[]> list = CSVFileUtils.readDataFromCSV(filePath);
        list.stream()
            .skip(1)
            .forEach(line -> {
                Book book = Book.builder()
                                .title(line[0])
                                .author(line[1])
                                .genre(line[2])
                                .publisher(line[3])
                                .build();

                bookRepository.save(book);
            });
    }

    /**
     *
     * @param filePath
     */

    public void readBorrowedBooks(String filePath) {

        borrowRepository.deleteAll();

        List<String[]> list = CSVFileUtils.readDataFromCSV(filePath);
        list.stream()
            .skip(1)
            .forEach(line -> {
                User user = userRepository.findByName(line[0]);
                Book book = bookRepository.findByTitle(line[1]);
                Borrow borrow = Borrow.builder()
                                      .user(user)
                                      .book(book)
                                      .borrowedFrom(TUtils.convertToTimestamp(line[2]))
                                      .borrowedTo(TUtils.convertToTimestamp(line[3]))
                                      .build();

                borrowRepository.save(borrow);
            });
    }
}