package com.shopdlow.notification_service.security.exception;

import com.shopdlow.notification_service.exceptions.NotificationFlowException;

public class UnsupportedAuthenticationModeException extends NotificationFlowException {
    public UnsupportedAuthenticationModeException(String message) {
        super(message);
    }
}
