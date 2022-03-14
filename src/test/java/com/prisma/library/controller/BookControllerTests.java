package com.prisma.library.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest()
@ActiveProfiles("test")
public class BookControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get_available_books_test()
    throws Exception {

        mockMvc.perform(get("/book/available"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(jsonPath("$[0].book_id").value(13));
    }
    @Test
    public void get_borrowed_by_books_on_date_range_test()
    throws Exception {

        mockMvc.perform(get("/book/borrowed-by?userId=1&from=01/01/2000&to=01/01/2021"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(jsonPath("$[0].book_id").value(36));
    }
    @Test
    public void get_borrowed_by_book_on_date_range_missing_params_test()
    throws Exception {

        mockMvc.perform(get("/book/borrowed-by?"))
               .andExpect(status().is(400));
    }

}
