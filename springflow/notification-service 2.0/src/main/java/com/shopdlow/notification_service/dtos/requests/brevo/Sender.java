package com.shopdlow.notification_service.dtos.requests.brevo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sender {
    private String email;
    private String name;
}
