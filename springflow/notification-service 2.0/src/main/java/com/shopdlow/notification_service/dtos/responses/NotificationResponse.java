package com.shopdlow.notification_service.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationResponse {
    private String eventId;
    private String status;
    private String userId;
    private String channel;
    private String recipient;
}
