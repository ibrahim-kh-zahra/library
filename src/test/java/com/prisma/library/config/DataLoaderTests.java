package com.prisma.library.config;

import com.prisma.library.config.DataLoader;
import com.prisma.library.repositories.BookRepository;
import com.prisma.library.repositories.BorrowRepository;
import com.prisma.library.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DataLoaderTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository   bookRepository;
    @Autowired
    BorrowRepository borrowRepository;
    @Test
    public void user_table_initialization_test(){
        assertTrue(userRepository.findAll().size()>0);
    }
    @Test
    public void book_table_initialization_test(){
        assertTrue(bookRepository.findAll().size()>0);
    }
    @Test
    public void borrow_table_initialization_test(){
        assertTrue(borrowRepository.findAll().size()>0);
    }
}
