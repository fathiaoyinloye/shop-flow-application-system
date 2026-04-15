package com.productservice.services.interfaces;

import com.productservice.dtos.request.CreateProductRequest;
import com.productservice.dtos.responses.CreateProductResponse;

public interface ProductService {
    CreateProductResponse addProduct(CreateProductRequest addProductRequest);

}
