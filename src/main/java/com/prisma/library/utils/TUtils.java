package com.prisma.library.utils;

import com.prisma.library.exceptions.InvalidDateFormatException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TUtils {

    /**
     * Convert datw with format MM/dd/yyy to timestamp object
     * @param date
     * @return
     */
    public static Timestamp convertToTimestamp(String date) {

        if (date == null || date.isBlank()) {
            return null;
        }
        String pattern = "MM/dd/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            LocalDate localDate = LocalDate.from(formatter.parse(date));
            return Timestamp.valueOf(localDate.atStartOfDay());
        } catch (Exception e) {
            throw new InvalidDateFormatException("invalid date format", e);
        }
    }
}
