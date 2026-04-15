package com.shopdlow.notification_service.repository;

import com.shopdlow.notification_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByIsbn(Book book);
}
