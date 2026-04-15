package com.shopdlow.notification_service.service.interfaces;

import com.shopdlow.notification_service.dtos.requests.NotificationRequest;
import com.shopdlow.notification_service.dtos.responses.NotificationResponse;

public interface NotificationService {
    public NotificationResponse send(NotificationRequest notificationRequest);


}
