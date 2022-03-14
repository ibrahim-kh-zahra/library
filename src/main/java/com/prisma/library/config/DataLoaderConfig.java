package com.prisma.library.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class DataLoaderConfig {

    @Value("${csv.user.path}")
    private String userCsvPath;
    @Value("${csv.book.path}")
    private String bookCsvPath;
    @Value("${csv.borrowed.path}")
    private String borrowedCsvPath;
}
