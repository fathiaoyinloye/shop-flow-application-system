package com.shopdlow.notification_service.Exceptions;

public class BookAlreadyExistException extends RuntimeException {
    public BookAlreadyExistException() {
        super("Book Already Exist");
    }
}
