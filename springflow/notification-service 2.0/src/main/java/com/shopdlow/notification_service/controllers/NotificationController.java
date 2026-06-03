package com.shopdlow.notification_service.controllers;

import com.shopdlow.notification_service.dtos.requests.NotificationRequest;
import com.shopdlow.notification_service.service.interfaces.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> sendNotification(@Valid @RequestBody NotificationRequest notificationRequest){
        return ResponseEntity.ok(notificationService.send(notificationRequest));
    }

    @GetMapping
    public String demo(){
        return "Demo!";
    }
}
