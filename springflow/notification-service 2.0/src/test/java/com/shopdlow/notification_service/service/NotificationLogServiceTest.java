package com.shopdlow.notification_service.service;

import com.shopdlow.notification_service.dtos.requests.NotificationRequest;
import com.shopdlow.notification_service.dtos.responses.NotificationResponse;
import com.shopdlow.notification_service.service.implementation.NotificationServiceImpl;
import com.shopdlow.notification_service.service.interfaces.NotificationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.shopdlow.notification_service.model.Channel.EMAIL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NotificationLogServiceTest {

    private final NotificationService notificationService = new NotificationServiceImpl();
    @Test
    @DisplayName(
            """
            Given:
            - I have a working notification service
            - I have a valid notification  message
            When:
            - I send message
            Assert:
            - I have recieve response with succes message
            - That status of the response is success
            """)
    void testCanSendNotification(){
        NotificationRequest notificationRequest = buildNotificationRequest();
        notificationRequest.setRecipient("fathiaoyinloye21@gmail.com");
        notificationRequest.setBody("This is a test message");
        notificationRequest.setChannel(EMAIL);
        NotificationResponse response = notificationService.send(notificationRequest);
        assertThat(response).isNotNull();
        assertThat(response.getEventId()).isNotNull();
        assertThat(response.getEventId()).isNotEmpty();
    }

    private static  NotificationRequest buildNotificationRequest() {
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setRecipient("fathiaoyinloye21@gmail.com");
        notificationRequest.setBody("This is a test message");
        notificationRequest.setChannel(EMAIL);
        notificationRequest.setSubject("test");
        return notificationRequest;
    }
}
