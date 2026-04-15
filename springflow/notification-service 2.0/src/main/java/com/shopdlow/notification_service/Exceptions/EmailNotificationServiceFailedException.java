package com.shopdlow.notification_service.Exceptions;

public class EmailNotificationServiceFailedException extends RuntimeException {
    public EmailNotificationServiceFailedException(String message){
        super(message);
    }
}
