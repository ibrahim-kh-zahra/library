package com.prisma.library.exceptions;

/**
 * Accepted format MM/dd/yyyy
 * else this exception will be thrown
 */
public class InvalidDateFormatException
    extends RuntimeException {

    public InvalidDateFormatException(String message, Throwable err) {

        super(message, err);
    }
}
