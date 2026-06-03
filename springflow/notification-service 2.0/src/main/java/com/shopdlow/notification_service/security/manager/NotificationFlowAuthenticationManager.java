package com.shopdlow.notification_service.security.manager;

import com.shopdlow.notification_service.security.exception.UnsupportedAuthenticationModeException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Set;

@AllArgsConstructor
@Component
public class NotificationFlowAuthenticationManager implements AuthenticationManager {
    private final Set<AuthenticationProvider> authenticationProviders;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        AuthenticationProvider authProvider  = authenticationProviders.stream()
                                                                .filter(authenticationProvider ->
                                                                        authenticationProvider.supports(authentication.getClass()))
                                                                .findFirst()
                                                                .orElseThrow(()->new UnsupportedAuthenticationModeException("authentication not supported"));
        return authProvider.authenticate(authentication);
    }
}
