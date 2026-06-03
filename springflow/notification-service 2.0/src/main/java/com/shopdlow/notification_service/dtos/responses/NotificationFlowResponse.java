package com.shopdlow.notification_service.dtos.responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationFlowResponse<T>{

    private T data;
    private String message;
    private String status;
    private List<String> errors;

    public  NotificationFlowResponse<T> buildSuccessResponse(T data, String message, List<String> errors, String status){
        NotificationFlowResponse<T> response = new NotificationFlowResponse<>();
        response.setData(data);
        response.setMessage(message);
        response.setErrors(errors);
        response.setStatus(status);
        return response;
    }

    public NotificationFlowResponse<T> buildErrorResponse(List<String> errors){
        NotificationFlowResponse<T> response = new NotificationFlowResponse<>();
        response.setErrors(errors);
        return response;

    }

}
