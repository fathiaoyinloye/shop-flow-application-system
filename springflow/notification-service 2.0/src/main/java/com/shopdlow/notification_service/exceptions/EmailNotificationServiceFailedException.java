package com.shopdlow.notification_service.exceptions;

public class EmailNotificationServiceFailedException extends RuntimeException {
    public EmailNotificationServiceFailedException(String message){
        super(message);
    }
}
