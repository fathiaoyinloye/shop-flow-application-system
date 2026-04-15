package com.productservice.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductResponse {
    private String productId;
    private String productName;
    private String message;
    private String createdAt;

}
