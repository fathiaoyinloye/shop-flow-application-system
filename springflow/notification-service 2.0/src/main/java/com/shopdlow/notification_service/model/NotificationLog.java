package com.shopdlow.notification_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NotificationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String eventId;
    private String userId;
    private String recipient;
    private String subject;
    private String body;
    private String status;



}
