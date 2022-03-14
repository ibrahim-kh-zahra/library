package com.prisma.library.controllers;

import com.prisma.library.models.User;
import com.prisma.library.services.UserService;
import com.prisma.library.utils.TUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getBorrowingUsers() {
        return userService.getBorrowingUsers();
    }
    @GetMapping("/borrowed-on")
    public List<User> getBorrowingUsersOnDate(@RequestParam String date ) {
        return userService.getBorrowingUsersOnDate(TUtils.convertToTimestamp(date));
    }
    @GetMapping("/non-terminated")
    public List<User> getNonTerminatingUsersNoBorrowing() {
        return userService.getNonTerminatingUsersNoBorrowing();
    }
}
