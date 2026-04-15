package com.productservice.data.repositories;

import com.productservice.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository <Product, UUID>{
}
