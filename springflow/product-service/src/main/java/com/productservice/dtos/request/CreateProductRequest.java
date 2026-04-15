package com.productservice.dtos.request;

import com.productservice.data.models.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {
    private String name;
    private Long price;
    private String description;
    private ProductCategory category;
}
