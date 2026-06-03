package com.shopdlow.notification_service.dtos.requests;

import com.shopdlow.notification_service.model.Channel;
import com.shopdlow.notification_service.model.Type;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {

    private String userId;
    private String recipient;
    private String subject;
    private String body;
    private Type type;
    private Channel channel;
}
