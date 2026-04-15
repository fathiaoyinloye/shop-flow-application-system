package com.shopdlow.notification_service.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailResponse {
    private String eventId;
    private String status;
    private String userId;
    private String channel;
    private String recipient;
}
