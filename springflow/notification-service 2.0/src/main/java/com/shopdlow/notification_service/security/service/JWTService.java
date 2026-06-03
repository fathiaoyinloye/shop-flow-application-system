package com.shopdlow.notification_service.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String generateToken(Authentication user);
    UserDetails verify(String jwt);
}
