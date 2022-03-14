package com.prisma.library.services;

import com.prisma.library.models.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUser(User user);

    List<User> getBorrowingUsers();

    List<User> getBorrowingUsersOnDate(Timestamp date);

    List<User> getNonTerminatingUsersNoBorrowing();
}
