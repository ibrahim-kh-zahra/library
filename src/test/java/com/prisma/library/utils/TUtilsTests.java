package com.prisma.library.utils;

import com.prisma.library.exceptions.InvalidDateFormatException;
import com.prisma.library.utils.TUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TUtilsTests {
    @Test
    public void null_date_tests(){
        assertEquals(TUtils.convertToTimestamp(null),null);
    }
    @Test
    public void basic_conversion_test(){
        assertEquals(TUtils.convertToTimestamp("11/11/2000").toString(),"2000-11-11 00:00:00.0");
    }
    @Test
    public void invalid_format_test(){
        Throwable exception = assertThrows(InvalidDateFormatException.class, () -> TUtils.convertToTimestamp("11.11.2021"));
        assertEquals("invalid date format", exception.getMessage());    }
}
