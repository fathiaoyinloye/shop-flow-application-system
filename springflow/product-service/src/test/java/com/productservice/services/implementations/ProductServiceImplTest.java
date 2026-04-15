package com.productservice.services.implementations;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.productservice.data.models.Product;
import com.productservice.data.models.ProductCategory;
import com.productservice.data.repositories.ProductRepository;
import com.productservice.dtos.request.CreateProductRequest;
import com.productservice.dtos.responses.CreateProductResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private CreateProductRequest mapCreateProductRequest(){
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setName("Test Product");
        createProductRequest.setPrice(100L);
        createProductRequest.setDescription("The Test Product Description");
        createProductRequest.setCategory(ProductCategory.OTHERS);
        return createProductRequest;

    }

    @Test
    void testCanAddProduct() {
//        CreateProductRequest request = new CreateProductRequest();
//        Product product = new Product();
//        CreateProductResponse createProductResponse = new CreateProductResponse();
//        createProductResponse.setProductId(product.getId().toString());
//        createProductResponse.setCreatedAt(Instant.now().toString());
//        createProductResponse.setProductId(UUID.randomUUID().toString());
//        createProductResponse.setMessage(request.getName() + " added successfully");
//
//        when(productService.addProduct(request)).thenReturn(createProductResponse);
//        CreateProductResponse response = productService.addProduct(mapCreateProductRequest());
//        assertNotNull(response);
//        assertThat(response.getProductId()).isNotNull();
//        assertThat(response.getCreatedAt()).isNotNull();

        CreateProductRequest request = new CreateProductRequest();
        request.setName("Test Product");

        UUID generatedId = UUID.randomUUID();
        Product savedProduct = new Product();
        savedProduct.setId(generatedId);
        savedProduct.setName(request.getName());

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
        CreateProductResponse response = productService.addProduct(request);

        assertNotNull(response);
        assertThat(response.getProductId()).isEqualTo(generatedId.toString());
        assertThat(response.getMessage()).contains("Test Product");

    }


}