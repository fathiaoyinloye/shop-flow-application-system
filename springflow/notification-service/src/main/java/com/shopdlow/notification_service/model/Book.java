package com.shopdlow.notification_service.model;

import jakarta.persistence.Entity;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
public class Book {
    private String isbn;
    @Id
    private Long id;
    private String title;
}
