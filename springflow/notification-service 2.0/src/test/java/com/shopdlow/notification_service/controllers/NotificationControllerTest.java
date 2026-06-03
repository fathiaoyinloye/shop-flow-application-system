package com.shopdlow.notification_service.controllers;

import com.shopdlow.notification_service.dtos.requests.NotificationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

import static com.shopdlow.notification_service.model.Channel.EMAIL;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithMockUser(authorities = {"USER", "TEST"})
    void testCanSendNotification(){
        try {
            NotificationRequest notificationRequest = new NotificationRequest();
            notificationRequest.setBody("This is a test message");
            notificationRequest.setRecipient("john@gmail.com");
            notificationRequest.setSubject("test");
            notificationRequest.setChannel(EMAIL);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/notification")
            .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(notificationRequest)))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        }catch (Exception ex){
            ex.printStackTrace();
            assertNull(ex);
        }
    }

}