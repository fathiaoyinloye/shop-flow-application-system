package com.shopdlow.notification_service.service;

import com.shopdlow.notification_service.Exceptions.BookAlreadyExistException;
import com.shopdlow.notification_service.model.Book;
import com.shopdlow.notification_service.repository.BookRepository;


public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository){
       this.repository = repository;
    }
    public Book addBook(Book book){
        if(repository.findByIsbn(book).isPresent())throw  new BookAlreadyExistException();
        return repository.save(book);
    }
}
