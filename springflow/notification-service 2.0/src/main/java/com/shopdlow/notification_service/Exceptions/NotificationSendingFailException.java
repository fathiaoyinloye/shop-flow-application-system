package com.shopdlow.notification_service.Exceptions;

public class NotificationSendingFailException extends RuntimeException {
    public NotificationSendingFailException(String message) {
        super(message);
    }
}
