package com.shopdlow.notification_service.service;

import com.shopdlow.notification_service.model.Book;
import com.shopdlow.notification_service.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository repository;
    @InjectMocks
    private BookService bookService;

    @Test
    void testCanAddBook(){

        Book book = new Book();
        Book addedBook = new Book();
        addedBook.setId(1L);
        when(repository.save(book)).thenReturn(addedBook);
        Book savedBook = bookService.addBook(book);
        assertNotNull(savedBook);
        assertThat(savedBook.getId()).isNotNull();
    }
    @Test
    void testCanBookAlreadyExistedCannotAddBook(){

        Book book = new Book();
        Book addedBook = new Book();
        addedBook.setId(1L);
        when(repository.save(book)).thenReturn(addedBook);
        Book savedBook = bookService.addBook(book);
        assertNotNull(savedBook);
        assertThat(savedBook.getId()).isNotNull();
    }




}
