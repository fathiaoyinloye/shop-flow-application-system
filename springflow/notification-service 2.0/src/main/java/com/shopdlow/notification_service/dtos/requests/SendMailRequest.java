package com.shopdlow.notification_service.dtos.requests;

import com.shopdlow.notification_service.model.Channel;
import com.shopdlow.notification_service.model.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailRequest {

    private String userId;
    private String recipient;
    private String subject;
    private String body;
    private Type type;
    private Channel channel;
}
