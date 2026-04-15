package com.productservice.data.models;


import java.util.List;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private Long price;
    private String description;
    private List<String> imagesURLs;
    private ProductCategory category;
    private List<String> tags;
    private Boolean isActive;
    private String createdAt;
    private Long updatedAt;

}
