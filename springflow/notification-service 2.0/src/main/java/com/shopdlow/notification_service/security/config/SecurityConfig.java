package com.shopdlow.notification_service.security.config;

import com.shopdlow.notification_service.security.filter.NotificationFlowAuthenticationFilter;
import com.shopdlow.notification_service.security.filter.NotificationFlowAuthorizationFilter;
import com.shopdlow.notification_service.security.manager.NotificationFlowAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, NotificationFlowAuthenticationFilter authenticationFilter, NotificationFlowAuthorizationFilter authorizationFilter){
        return httpSecurity
                .addFilterAt(authenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(authorizationFilter, NotificationFlowAuthenticationFilter.class)
                .csrf(c->c.disable())
                .authorizeHttpRequests(r->r.requestMatchers(HttpMethod.POST,"/login").permitAll())
                .authorizeHttpRequests(r->r.requestMatchers(HttpMethod.POST,"/api/v1/notification").hasRole("ADMIN"))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        List<UserDetails> users = List.of(
                User.withUsername("jonsnow@email.com").password("the_north_remembers_111").roles("USER").build(),
                User.withUsername("ramsey@email.com").password("password").roles("ADMIN").build()
        );
        return new InMemoryUserDetailsManager(users);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
