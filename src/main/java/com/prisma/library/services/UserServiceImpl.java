package com.prisma.library.services;

import com.prisma.library.models.User;
import com.prisma.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl
    implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(final User user) {

        return null;
    }

    @Override
    public User getUser(final User user) {

        return null;
    }

    @Override
    public List<User> getBorrowingUsers() {

        return userRepository.findBorrowingUser();
    }

    @Override
    public List<User> getBorrowingUsersOnDate(final Timestamp date) {
        System.out.println(date);

        return userRepository.findBorrowingUsersOnDate(date);
    }

    @Override
    public List<User> getNonTerminatingUsersNoBorrowing() {

        return userRepository.findNonTerminatedUsersNoBorrowing();
    }
}
