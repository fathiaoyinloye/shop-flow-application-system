package com.shopdlow.notification_service.exceptions;

public class NotificationSendingFailException extends RuntimeException {
    public NotificationSendingFailException(String message) {
        super(message);
    }
}
