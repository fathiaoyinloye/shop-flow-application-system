package com.productservice.services.implementations;

import com.productservice.data.models.Product;
import com.productservice.data.repositories.ProductRepository;
import com.productservice.dtos.request.CreateProductRequest;
import com.productservice.dtos.responses.CreateProductResponse;
import com.productservice.services.interfaces.ProductService;

import java.time.Instant;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CreateProductResponse addProduct(CreateProductRequest addProductRequest) {
        Product product = new Product();
        product.setName(addProductRequest.getName());
        product.setPrice(addProductRequest.getPrice());
        product.setDescription(addProductRequest.getDescription());
        product.setId(UUID.randomUUID());
        product.setCreatedAt(Instant.now().toString());
        product.setCategory(addProductRequest.getCategory());
        Product savedProduct = productRepository.save(product);

        CreateProductResponse response = new CreateProductResponse();
        response.setProductId(savedProduct.getId().toString());
        response.setCreatedAt(savedProduct.getCreatedAt());
        response.setMessage(savedProduct.getName() + " added successfully");

        return response;
    }
}
