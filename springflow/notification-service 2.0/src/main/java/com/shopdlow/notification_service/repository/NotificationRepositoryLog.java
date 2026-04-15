package com.shopdlow.notification_service.repository;

import com.shopdlow.notification_service.model.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepositoryLog extends JpaRepository<NotificationLog, String> {
}
