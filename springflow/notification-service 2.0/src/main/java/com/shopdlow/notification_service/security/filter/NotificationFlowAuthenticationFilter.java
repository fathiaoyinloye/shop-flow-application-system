package com.shopdlow.notification_service.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.shopdlow.notification_service.security.dto.request.LoginRequest;
import com.shopdlow.notification_service.security.dto.response.LoginResponse;
import com.shopdlow.notification_service.security.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;


@Component
@AllArgsConstructor
public class NotificationFlowAuthenticationFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //1. Get json data from request body
        boolean isAuthRequest = request.getMethod().equals(HttpMethod.POST.name()) && request.getServletPath().equals("/login");
        if (isAuthRequest) {
            InputStream requestStream = request.getInputStream();
            LoginRequest loginRequest = objectMapper.readValue(requestStream, LoginRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authResult = authenticationManager.authenticate(authentication);

            String token = jwtService.generateToken(authResult);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccessToken(token);
            loginResponse.setType("Bearer");
//            loginResponse.setUserId();
            response.setContentType("application/json");
            response.getOutputStream().write(objectMapper.writeValueAsBytes(loginResponse));
            response.flushBuffer();
            return;
        }
        filterChain.doFilter(request, response);

    }
}
