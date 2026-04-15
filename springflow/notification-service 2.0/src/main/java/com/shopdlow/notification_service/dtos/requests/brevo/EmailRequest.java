package com.shopdlow.notification_service.dtos.requests.brevo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmailRequest {
    private String htmlContent;
    private Sender sender;
    private String subject;
    @JsonProperty("to")
    private List<Recipient> recipients;
}
