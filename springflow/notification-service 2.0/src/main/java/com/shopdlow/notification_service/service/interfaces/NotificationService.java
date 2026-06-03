package com.shopdlow.notification_service.service.interfaces;

import com.shopdlow.notification_service.dtos.requests.NotificationRequest;
import com.shopdlow.notification_service.dtos.responses.NotificationResponse;
import org.springframework.stereotype.Service;

public interface NotificationService {
     NotificationResponse send(NotificationRequest notificationRequest);


}
