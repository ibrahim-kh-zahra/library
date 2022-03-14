package com.prisma.library.repositories;

import com.prisma.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.Timestamp;
import java.util.List;

public interface UserRepository
    extends JpaRepository<User, Long> {

    public User findByName(String name);

    @Query("SELECT  Distinct user FROM User user inner join user.borrows as br")
    public List<User> findBorrowingUser();

    @Query("SELECT user FROM User user inner join user.borrows as br WHERE br.borrowedFrom=:date")
    public List<User> findBorrowingUsersOnDate(Timestamp date);

    @Query("SELECT user FROM User user left join user.borrows as br WHERE user.expiringDate>current_timestamp() AND(br is Null or br.borrowedTo < " +
           "current_timestamp())")
    public List<User> findNonTerminatedUsersNoBorrowing();
}
