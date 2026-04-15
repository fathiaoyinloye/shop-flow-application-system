package com.shopdlow.notification_service.service.mail;

import com.shopdlow.notification_service.dtos.requests.SendMailRequest;
import com.shopdlow.notification_service.dtos.responses.SendMailResponse;

public interface MailService {
    SendMailResponse send (SendMailRequest request);
}
