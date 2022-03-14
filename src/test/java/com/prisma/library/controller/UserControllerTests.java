package com.prisma.library.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest()
@ActiveProfiles("test")
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get_borrowing_users_at_least_one_book_test()
    throws Exception {

        mockMvc.perform(get("/user"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(jsonPath("$[0].user_id").value(1))
               .andExpect(jsonPath("$[0].name").value("Aexi,Liam"));
    }

    @Test
    public void get_borrowing_users_on_specific_date_test()
    throws Exception {

        mockMvc.perform(get("/user/borrowed-on?date=05/14/2009"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(jsonPath("$[0].user_id").value(4))
               .andExpect(jsonPath("$[0].name").value("Chish,Elijah"));
    }

    @Test
    public void get_borrowing_users_on_no_borrows_date_test()
    throws Exception {

        mockMvc.perform(get("/user/borrowed-on?date=05/14/2021"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void get_non_terminated_users_with_no_borrows_test()
    throws Exception {

        mockMvc.perform(get("/user/non-terminated"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(jsonPath("$[0].user_id").value(6));
    }
}
